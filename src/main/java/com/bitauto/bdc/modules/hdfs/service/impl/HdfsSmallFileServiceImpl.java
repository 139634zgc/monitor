package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.hdfs.dao.HdfsSmallFileDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileDailyStatisEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileDailyStatisService;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/17.
 */
@Service("hdfsSmallFileService")
public class HdfsSmallFileServiceImpl implements HdfsSmallFileService {

    @Value("${hdfs.fsimage.delimited-file}")
    private String fsimageDelimitedFile;

    @Autowired
    private HdfsSmallFileDao hdfsSmallFileDao;

    @Autowired
    private HdfsSmallFileDailyStatisService hdfsSmallFileDailyStatisService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<HdfsSmallFileEntity> queryList(Map<String, Object> map) {
        return hdfsSmallFileDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsSmallFileDao.queryTotal(map);
    }

    @Override
    public void addBatch() {
        HashMap<String, HdfsSmallFileEntity> hdfsSmallFileEntityHashMap = handleFsimageFile();
        List insertList = new ArrayList<HdfsSmallFileEntity>();
        Iterator iterator = hdfsSmallFileEntityHashMap.entrySet().iterator();

        logger.info("total smallfile directory count:" + hdfsSmallFileEntityHashMap.size());

        Long maxHdfsSmallfilesId = null;
        boolean firstSaved = false;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            HdfsSmallFileEntity hdfsSmallFileEntity = (HdfsSmallFileEntity) entry.getValue();

            if(!firstSaved) {
                hdfsSmallFileDao.save(hdfsSmallFileEntity);
                maxHdfsSmallfilesId = hdfsSmallFileEntity.getHdfsSmallfileId();
                firstSaved = true;
                continue;
            }

            if(insertList.size() < 50) {
                insertList.add(hdfsSmallFileEntity);
            } else {
                insertList.add(hdfsSmallFileEntity);
                hdfsSmallFileDao.saveBatch(insertList);
                insertList.clear();
            }
        }
        if(insertList.size() > 0) {
            hdfsSmallFileDao.saveBatch(insertList);
        }

        deleteHistory(maxHdfsSmallfilesId);
    }

    public void deleteHistory(Long maxHdfsSmallfilesId) {
        int count = hdfsSmallFileDao.delete(maxHdfsSmallfilesId);
        while (count > 0) {
            count = hdfsSmallFileDao.delete(maxHdfsSmallfilesId);
        }
    }

    public HashMap<String, HdfsSmallFileEntity> handleFsimageFile() {
        HashMap<String, HdfsSmallFileEntity> dirMap = new HashMap<>(80000);

        try {
            FileInputStream in = new FileInputStream(fsimageDelimitedFile);
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            int i = 1;
            while((line = bufReader.readLine()) != null){
                filterLine(line, dirMap);
                i++;
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            logger.error("read fsimage file exception" + fsimageDelimitedFile);
            return null;
        }

        aggregateSmallFile(dirMap);

        return dirMap;
    }

    private void aggregateSmallFile(HashMap<String, HdfsSmallFileEntity> dirMap) {
        logger.info("total file directory count:" + dirMap.size());

        Iterator iterator = dirMap.entrySet().iterator();

        Long smallFileCount = 0L;
        Long smallDirCount = 0L;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            HdfsSmallFileEntity hdfsSmallFileEntity = (HdfsSmallFileEntity)entry.getValue();
            if(hdfsSmallFileEntity.getTotalSize() == 0 || hdfsSmallFileEntity.getFileCount() == 0) {
                iterator.remove();
                continue;
            }
            Long avgSize = hdfsSmallFileEntity.getTotalSize() / hdfsSmallFileEntity.getFileCount();
            if(avgSize > 67108864 || hdfsSmallFileEntity.getFileCount() < avgSize/(1024*1024)) {
                iterator.remove();
                continue;
            }
            smallFileCount += hdfsSmallFileEntity.getFileCount();
            smallDirCount++;
            hdfsSmallFileEntity.setAvgSize(avgSize);
            hdfsSmallFileEntity.setCreateTime(new Date());
        }

        HdfsSmallFileDailyStatisEntity hdfsSmallFileDailyStatisEntity = new HdfsSmallFileDailyStatisEntity();
        Date date = new Date();
        String today = DateUtils.format(date);
        hdfsSmallFileDailyStatisEntity.setCreateDate(today);
        hdfsSmallFileDailyStatisEntity.setCreateTime(date);
        hdfsSmallFileDailyStatisEntity.setSmallDirCount(smallDirCount);
        hdfsSmallFileDailyStatisEntity.setSmallFileCount(smallFileCount);
        hdfsSmallFileDailyStatisService.saveAndExpire(hdfsSmallFileDailyStatisEntity);
    }

    private void filterLine(String line, HashMap<String, HdfsSmallFileEntity> dirMap) {
        String[]  separateLine = line.split(",");
        if(separateLine.length != 12) {
            return;
        }
        if(separateLine[9].startsWith("d")) {
            return;
        }
        if(!StringUtils.isNumeric(separateLine[6])) {
            return;
        }
        String directory = StringUtils.substringBeforeLast(line, "/");
        if(StringUtils.isBlank(directory)) {
            return;
        }

        if(dirMap.containsKey(directory)) {
            HdfsSmallFileEntity hdfsSmallFileEntity = dirMap.get(directory);
            hdfsSmallFileEntity.setFileCount(hdfsSmallFileEntity.getFileCount() + 1);
            hdfsSmallFileEntity.setTotalSize(hdfsSmallFileEntity.getTotalSize() + new Long(separateLine[6]));
        } else {
            HdfsSmallFileEntity hdfsSmallFileEntity = new HdfsSmallFileEntity();
            hdfsSmallFileEntity.setDirectory(directory);
            hdfsSmallFileEntity.setFileCount(1L);
            hdfsSmallFileEntity.setTotalSize(new Long(separateLine[6]));
            hdfsSmallFileEntity.setUsername(separateLine[10]);
            dirMap.put(directory, hdfsSmallFileEntity);
        }
    }
}

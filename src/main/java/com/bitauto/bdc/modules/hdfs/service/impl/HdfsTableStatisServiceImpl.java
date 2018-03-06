package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.common.annotation.DataSource;
import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.hdfs.dao.HdfsDbStatisDao;
import com.bitauto.bdc.modules.hdfs.dao.HdfsTableDao;
import com.bitauto.bdc.modules.hdfs.dao.HdfsTableStatisDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsTableStatisService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/30.
 */
@Service("hdfsTableStatis")
public class HdfsTableStatisServiceImpl implements HdfsTableStatisService {
    @Autowired
    private HdfsTableDao hdfsTableDao;

    @Autowired
    private HdfsTableStatisDao hdfsTableStatisDao;

    @Autowired
    private HdfsDbStatisDao hdfsDbStatisDao;

    @Value("${hdfs.fsimage.delimited-file}")
    private String fsimageDelimitedFile;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public List<HdfsTableEntity> queryList(Map<String, Object> map) {
        List<HdfsTableEntity> list = hdfsTableStatisDao.queryList(map);
        for (int i = 0; i < list.size(); i++) {
            HdfsTableEntity hdfsTableEntity = list.get(i);
            hdfsTableEntity.setTblSize(hdfsTableEntity.getTblSize()/(1024*1024));
            list.set(i, hdfsTableEntity);
        }
        return list;
    }

    @Override
    public List<HdfsTableEntity> listIncreaseDiskTop(Map<String, Object> map) {
        List<HdfsTableEntity> hdfsTableEntities;
        if(map.containsKey("startTime")) {
            map.put("endTime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            hdfsTableEntities = hdfsTableStatisDao.listDiskTopByDateRange(map);
        } else {
            Date date = new Date();
            map.put("createDate", DateUtils.format(date));
            hdfsTableEntities = hdfsTableStatisDao.listDiskIncreaseTop(map);
            if (hdfsTableEntities.isEmpty()) {
                map.put("createDate", DateUtils.getLastDay(date));
                hdfsTableEntities = hdfsTableStatisDao.listDiskIncreaseTop(map);
            }
        }
        formatTableEntityList(hdfsTableEntities);
        return hdfsTableEntities;
    }

    @Override
    public List<HdfsTableEntity> listDiskTop(Map<String, Object> map) {
        Date date = new Date();
        map.put("createDate", DateUtils.format(date));
        List<HdfsTableEntity> hdfsTableEntities =  hdfsTableStatisDao.listDiskTop(map);
        if(hdfsTableEntities.isEmpty()) {
            map.put("createDate", DateUtils.getLastDay(date));
            hdfsTableEntities =  hdfsTableStatisDao.listDiskTop(map);
        }
        formatTableEntityList(hdfsTableEntities);
        return hdfsTableEntities;
    }

    private void formatTableEntityList(List<HdfsTableEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            HdfsTableEntity hdfsTableEntity = list.get(i);
            hdfsTableEntity.setTblSize(hdfsTableEntity.getTblSize()/(1024*1024*1024));
            hdfsTableEntity.setIncreaseDisk(hdfsTableEntity.getIncreaseDisk()/(1024*1024*1024));
            list.set(i, hdfsTableEntity);
        }
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsTableStatisDao.queryTotal(map);
    }


    @DataSource(name = "dsHive")
    public List<HdfsTableEntity> processListByFsimage() {
        List<HdfsTableEntity> list = hdfsTableDao.queryList(new HashMap<>());
        for(int i = 0; i < list.size(); i++) {
            HdfsTableEntity hdfsTableEntity = list.get(i);

            String dBLocationURI = hdfsTableEntity.getDbLocation();
            dBLocationURI = handleLocationUri(dBLocationURI);
            hdfsTableEntity.setDbLocation(dBLocationURI);

            String tblLocation = hdfsTableEntity.getTblLocation();
            tblLocation = handleLocationUri(tblLocation);
            hdfsTableEntity.setTblLocation(tblLocation);

            list.set(i, hdfsTableEntity);
        }

        HashSet<String> locationSet = new HashSet<>();
        for (HdfsTableEntity hdfsTableEntity: list) {
            if(StringUtils.isNotBlank(hdfsTableEntity.getDbLocation())) {
                locationSet.add(hdfsTableEntity.getDbLocation());
            }
            if(StringUtils.isNotBlank(hdfsTableEntity.getTblLocation())) {
                locationSet.add(hdfsTableEntity.getTblLocation());
            }
        }

        HashMap<String, Long> dirMap = new HashMap<>(5000);
        try {
            FileInputStream in = new FileInputStream(fsimageDelimitedFile);
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            while((line = bufReader.readLine()) != null){
                calDirSize(line, locationSet, dirMap);
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            logger.error("read fsimage file exception" + fsimageDelimitedFile);
            return null;
        }

        for(int i = 0; i < list.size(); i++) {
            HdfsTableEntity hdfsTableEntity = list.get(i);
            if(dirMap.containsKey(hdfsTableEntity.getDbLocation())) {
                hdfsTableEntity.setDbSize(dirMap.get(hdfsTableEntity.getDbLocation()));
            } else {
                hdfsTableEntity.setDbSize(0L);
            }

            if(dirMap.containsKey(hdfsTableEntity.getTblLocation())) {
                hdfsTableEntity.setTblSize(dirMap.get(hdfsTableEntity.getTblLocation()));
            } else {
                hdfsTableEntity.setTblSize(0L);
            }

            list.set(i, hdfsTableEntity);
        }

        return list;
    }

    @Override
    @DataSource(name = "dsHive")
    public List<HdfsTableEntity> getMetaDbList() {
        return hdfsTableDao.queryDbList();
    }


    /*@Override
    public void addDbStatis(List<HdfsTableEntity> list) {
        HashMap<String, HdfsTableEntity> dbMap = new HashMap<>();
        for (HdfsTableEntity hdfsTableEntity: list) {
            if(StringUtils.isNotBlank(hdfsTableEntity.getDbLocation())) {
                dbMap.put(hdfsTableEntity.getDbLocation(), hdfsTableEntity);
            }
        }

        Date date = new Date();
        String today = DateUtils.format(date);
        List<HdfsDbEntity> dbList = new ArrayList<>();

        for(Map.Entry<String, HdfsTableEntity> entry: dbMap.entrySet()) {
            HdfsDbEntity hdfsDbEntity = new HdfsDbEntity();
            HdfsTableEntity hdfsTableEntity = entry.getValue();
            hdfsDbEntity.setDbLocation(hdfsTableEntity.getDbLocation());
            hdfsDbEntity.setDbName(hdfsTableEntity.getDbName());
            hdfsDbEntity.setDbOwner(hdfsTableEntity.getDbOwner());
            hdfsDbEntity.setDbSize(hdfsTableEntity.getDbSize());
            hdfsDbEntity.setCreateDate(today);
            dbList.add(hdfsDbEntity);
        }

        for(HdfsDbEntity hdfsDbEntity: dbList) {
            HashMap<String, Object> dbParams = new HashMap<>();
            dbParams.put("dbName", hdfsDbEntity.getDbName());
            dbParams.put("createDate", DateUtils.getLastDay(date));
            HdfsDbEntity lastDayHdfsDbEntity = hdfsDbStatisDao.queryByDbnameAndDate(dbParams);
            if(lastDayHdfsDbEntity != null) {
                hdfsDbEntity.setIncreaseDisk(hdfsDbEntity.getDbSize() - lastDayHdfsDbEntity.getDbSize());
            }
            HashMap<String, Object> dbUpdateParams = new HashMap<>();
            dbUpdateParams.put("dbName", hdfsDbEntity.getDbName());
            dbUpdateParams.put("createDate", DateUtils.format(date));
            hdfsDbStatisDao.updateNewestByDbnameAndDate(dbUpdateParams);
            hdfsDbStatisDao.save(hdfsDbEntity);
        }
    }*/

    // 需要table统计完后再执行
    @Override
    public void addDbStatis(List<HdfsTableEntity> list) {
        Date date = new Date();
        String today = DateUtils.format(date);

        List<HdfsDbEntity> dbList = new ArrayList<>();

        HashMap<String, Object> dbParam = new HashMap<>();
        for (HdfsTableEntity hdfsTableEntity: list) {
            if(StringUtils.isNotBlank(hdfsTableEntity.getDbName())) {
                dbParam.put("dbName", hdfsTableEntity.getDbName());
                dbParam.put("createDate", today);
                Long dbsize = hdfsTableStatisDao.queryDbSize(dbParam);
                if(dbsize == null) {
                    dbsize = 0L;
                }
                HdfsDbEntity hdfsDbEntity = new HdfsDbEntity();
                hdfsDbEntity.setDbSize(dbsize);
                hdfsDbEntity.setCreateDate(today);
                hdfsDbEntity.setDbLocation(handleLocationUri(hdfsTableEntity.getDbLocation()));
                hdfsDbEntity.setDbName(hdfsTableEntity.getDbName());
                hdfsDbEntity.setDbOwner(hdfsTableEntity.getDbOwner());
                dbList.add(hdfsDbEntity);
            }
        }

        for(HdfsDbEntity hdfsDbEntity: dbList) {
            HashMap<String, Object> dbParams = new HashMap<>();
            dbParams.put("dbName", hdfsDbEntity.getDbName());
            dbParams.put("createDate", DateUtils.getLastDay(date));
            HdfsDbEntity lastDayHdfsDbEntity = hdfsDbStatisDao.queryByDbnameAndDate(dbParams);
            if(lastDayHdfsDbEntity != null) {
                hdfsDbEntity.setIncreaseDisk(hdfsDbEntity.getDbSize() - lastDayHdfsDbEntity.getDbSize());
            }
            HashMap<String, Object> dbUpdateParams = new HashMap<>();
            dbUpdateParams.put("dbName", hdfsDbEntity.getDbName());
            dbUpdateParams.put("createDate", DateUtils.format(date));
            hdfsDbStatisDao.updateNewestByDbnameAndDate(dbUpdateParams);
            hdfsDbStatisDao.save(hdfsDbEntity);
        }
    }

    @Override
    public void addBatch(List<HdfsTableEntity> list) {
        Date date = new Date();
        String today = DateUtils.format(date);

        for(int i = 0; i < list.size(); i++) {
            HdfsTableEntity hdfsTableEntity = list.get(i);
            HashMap<String, Object> tableParam = new HashMap<>();
            tableParam.put("tableName", hdfsTableEntity.getTblName());
            tableParam.put("createDate", DateUtils.getLastDay(date));
            tableParam.put("dbName", hdfsTableEntity.getDbName());
            HdfsTableEntity lastDayHdfsTableEntity = hdfsTableStatisDao.queryByTableAndDate(tableParam);
            if(null == lastDayHdfsTableEntity) {
                continue;
            }
            hdfsTableEntity.setIncreaseDisk(hdfsTableEntity.getTblSize() - lastDayHdfsTableEntity.getTblSize());
            list.set(i, hdfsTableEntity);
        }

        for(HdfsTableEntity hdfsTableEntity: list) {
            HashMap<String, Object> tableParam = new HashMap<>();
            tableParam.put("tableName", hdfsTableEntity.getTblName());
            tableParam.put("createDate", DateUtils.format(date));
            tableParam.put("dbName", hdfsTableEntity.getDbName());
            hdfsTableStatisDao.updateNewestByTableAndDate(tableParam);
        }

        List<HdfsTableEntity> insertList = new ArrayList<>();

        for(HdfsTableEntity hdfsTableEntity: list) {
            hdfsTableEntity.setCreateDate(today);
            if(insertList.size() < 40) {
                insertList.add(hdfsTableEntity);
            } else {
                insertList.add(hdfsTableEntity);
                hdfsTableStatisDao.saveBatch(insertList);
                insertList.clear();
            }
        }

        if(insertList.size() > 0) {
            hdfsTableStatisDao.saveBatch(insertList);
        }

    }

    private String handleLocationUri(String location) {
        if(null == location) {
            return "";
        }
        String[] keyword = new String[]{"hdfs://sjxt-hd01:8020", "hdfs://bdc", "hdfs://cube1", "hdfs:///"};
        for (String item: keyword) {
            if(location.startsWith(item)) {
                return location.replaceFirst(item, "");
            }
        }
        return location;
    }


    private void calDirSize(String line, HashSet<String> locations, HashMap<String, Long> dirMap) {
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
        if(StringUtils.isBlank(separateLine[0])) {
            return;
        }

        for (String dbLocation: locations) {
            if(separateLine[0].startsWith(dbLocation)) {
                if(dirMap.containsKey(dbLocation)) {
                    dirMap.put(dbLocation, dirMap.get(dbLocation) + Long.parseLong(separateLine[6]));
                } else {
                    dirMap.put(dbLocation, Long.parseLong(separateLine[6]));
                }
            }
        }
    }

}

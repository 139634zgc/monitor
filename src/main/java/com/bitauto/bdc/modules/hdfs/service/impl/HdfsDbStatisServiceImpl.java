package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.hdfs.dao.HdfsDbStatisDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDbStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/5.
 */
@Service("hdfsDbStatisService")
public class HdfsDbStatisServiceImpl implements HdfsDbStatisService {
    @Autowired
    private HdfsDbStatisDao hdfsDbStatisDao;

    @Override
    public List<HdfsDbEntity> queryList(Map<String, Object> map) {
        List<HdfsDbEntity> list = hdfsDbStatisDao.queryList(map);
        for (int i = 0; i < list.size(); i++) {
            HdfsDbEntity hdfsDbEntity = list.get(i);
            hdfsDbEntity.setDbSize(hdfsDbEntity.getDbSize()/(1024*1024*1024));
            list.set(i, hdfsDbEntity);
        }
        return list;
    }

    @Override
    public HdfsDbEntity queryByDbName(String dbName) {
        return hdfsDbStatisDao.queryByDbName(dbName);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsDbStatisDao.queryTotal(map);
    }

    @Override
    public List<HdfsDbEntity> listIncreaseDiskTop(Map<String, Object> map) {
        List<HdfsDbEntity> hdfsDbEntities;
        if(map.containsKey("startTime")) {
            map.put("endTime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            hdfsDbEntities = hdfsDbStatisDao.listDiskTopByDateRange(map);
        } else {
            Date date = new Date();
            map.put("createDate", DateUtils.format(date));
            hdfsDbEntities = hdfsDbStatisDao.listDiskIncreaseTop(map);
            if(hdfsDbEntities.isEmpty()) {
                map.put("createDate", DateUtils.getLastDay(date));
                hdfsDbEntities = hdfsDbStatisDao.listDiskIncreaseTop(map);
            }
        }

        formatDbEntities(hdfsDbEntities);
        handleDbEntities(hdfsDbEntities);
        return hdfsDbEntities;
    }

    @Override
    public List<HdfsDbEntity> listDiskTop(Map<String, Object> map) {
        Date date = new Date();
        map.put("createDate", DateUtils.format(date));
        List<HdfsDbEntity> hdfsDbEntities = hdfsDbStatisDao.listDiskTop(map);
        if(hdfsDbEntities.isEmpty()) {
            map.put("createDate", DateUtils.getLastDay(date));
            hdfsDbEntities = hdfsDbStatisDao.listDiskTop(map);
        }
        formatDbEntities(hdfsDbEntities);
        handleDbEntities(hdfsDbEntities);
        return hdfsDbEntities;
    }

    private void handleDbEntities(List<HdfsDbEntity> list) {
        Iterator<HdfsDbEntity> iterator = list.iterator();
        while (iterator.hasNext()) {
            HdfsDbEntity hdfsDbEntity = iterator.next();
            if(hdfsDbEntity.getDbName().equals("default")) {
                iterator.remove();
            }
        }
    }

    private void formatDbEntities(List<HdfsDbEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            HdfsDbEntity hdfsDbEntity = list.get(i);
            hdfsDbEntity.setDbSize(hdfsDbEntity.getDbSize()/(1024*1024*1024));
            hdfsDbEntity.setIncreaseDisk(hdfsDbEntity.getIncreaseDisk()/(1024*1024*1024));
            list.set(i, hdfsDbEntity);
        }
    }
}

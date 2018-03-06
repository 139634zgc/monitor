package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/10/23.
 */
public interface HdfsMonitorItemService {
    List<HdfsMonitorItemEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    HdfsMonitorItemEntity add();

    int updateByPrimaryKeySelective(HdfsMonitorItemEntity hdfsMonitorItemEntity);

    Integer addDiskDailyIncrease(Date date);
}

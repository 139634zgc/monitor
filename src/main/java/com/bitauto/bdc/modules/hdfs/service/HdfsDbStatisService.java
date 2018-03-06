package com.bitauto.bdc.modules.hdfs.service;


import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/5.
 */
public interface HdfsDbStatisService {
    List<HdfsDbEntity> queryList(Map<String, Object> map);
    int queryTotal(Map<String, Object> map);
    List<HdfsDbEntity> listIncreaseDiskTop(Map<String, Object> map);
    List<HdfsDbEntity> listDiskTop(Map<String, Object> map);
    HdfsDbEntity queryByDbName(String dbName);
}

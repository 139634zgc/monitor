package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/30.
 */
public interface HdfsTableStatisService {
    List<HdfsTableEntity> queryList(Map<String, Object> map);
    List<HdfsTableEntity> processListByFsimage();
    void addBatch(List<HdfsTableEntity> list);
    int queryTotal(Map<String, Object> map);
    void addDbStatis(List<HdfsTableEntity> list);
    List<HdfsTableEntity> listIncreaseDiskTop(Map<String, Object> map);
    List<HdfsTableEntity> listDiskTop(Map<String, Object> map);
    List<HdfsTableEntity> getMetaDbList();
}

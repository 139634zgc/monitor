package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/17.
 */
public interface HdfsSmallFileService {
    HashMap<String, HdfsSmallFileEntity> handleFsimageFile();
    void addBatch();
    List<HdfsSmallFileEntity> queryList(Map<String, Object> map);
    int queryTotal(Map<String, Object> map);
}

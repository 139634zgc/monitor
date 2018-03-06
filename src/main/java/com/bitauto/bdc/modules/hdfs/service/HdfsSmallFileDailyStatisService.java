package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileDailyStatisEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/28.
 */
public interface HdfsSmallFileDailyStatisService {
    List<HdfsSmallFileDailyStatisEntity> queryList(Map<String, Object> map);
    int queryTotal(Map<String, Object> map);
    void saveAndExpire(HdfsSmallFileDailyStatisEntity hdfsSmallFileDailyStatisEntity);
}

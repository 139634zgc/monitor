package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/28.
 */
public interface HdfsDepartmentService {
    List<HdfsDepartmentEntity> queryList(Map<String, Object> map);
    int queryTotal(Map<String, Object> map);
    void update(HdfsDepartmentEntity hdfsDepartmentEntity);
    HdfsDepartmentEntity queryObject(Long hdfsDepartmentId);
    void save(HdfsDepartmentEntity hdfsDepartment);
    void deleteBatch(Long[] hdfsDepartmentIds);
}
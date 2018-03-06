package com.bitauto.bdc.modules.hdfs.service;

import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentDbEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/28.
 */
public interface HdfsDepartmentDbService {
    List<HdfsDepartmentDbEntity> queryList(Map<String, Object> map);
    void addDepartDiskStatis();
    HdfsDepartmentDbEntity queryObject(Long hdfsDepartmentDbId);
    int queryTotal(Map<String, Object> map);
    void save(HdfsDepartmentDbEntity hdfsDepartmentDb);
    void update(HdfsDepartmentDbEntity hdfsDepartmentDb);
    void deleteBatch(Long[] hdfsDepartmentDbIds);
}

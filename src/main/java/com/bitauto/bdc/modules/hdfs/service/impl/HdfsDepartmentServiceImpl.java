package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.modules.hdfs.dao.HdfsDepartmentDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/28.
 */
@Service("HdfsDepartment")
public class HdfsDepartmentServiceImpl implements HdfsDepartmentService {
    @Autowired
    private HdfsDepartmentDao hdfsDepartmentDao;

    @Override
    public List<HdfsDepartmentEntity> queryList(Map<String, Object> map) {
        return hdfsDepartmentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsDepartmentDao.queryTotal(map);
    }

    @Override
    public void update(HdfsDepartmentEntity hdfsDepartmentEntity) {
        hdfsDepartmentDao.update(hdfsDepartmentEntity);
    }

    @Override
    public HdfsDepartmentEntity queryObject(Long hdfsDepartmentId){
        return hdfsDepartmentDao.queryObject(hdfsDepartmentId);
    }

    @Override
    public void save(HdfsDepartmentEntity hdfsDepartment){
        hdfsDepartmentDao.save(hdfsDepartment);
    }

    @Override
    public void deleteBatch(Long[] hdfsDepartmentIds){
        hdfsDepartmentDao.deleteBatch(hdfsDepartmentIds);
    }
}

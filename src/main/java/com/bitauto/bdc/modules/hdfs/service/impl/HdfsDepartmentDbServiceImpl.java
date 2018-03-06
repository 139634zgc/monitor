package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.modules.hdfs.dao.HdfsDepartmentDbDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentDbEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDbStatisService;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentDbService;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/28.
 */
@Service("HdfsDepartmentDb")
public class HdfsDepartmentDbServiceImpl implements HdfsDepartmentDbService{
    @Autowired
    private HdfsDepartmentDbDao hdfsDepartmentDbDao;

    @Autowired
    private HdfsDepartmentService hdfsDepartmentService;

    @Autowired
    private HdfsDbStatisService hdfsDbStatisService;

    @Override
    public List<HdfsDepartmentDbEntity> queryList(Map<String, Object> map) {
        List<HdfsDepartmentEntity> hdfsDepartmentEntities = hdfsDepartmentService.queryList(new HashMap<>());
        HashMap<Long, String> departmentIdMap = new HashMap<>();
        for (HdfsDepartmentEntity hdfsDepartmentEntity: hdfsDepartmentEntities) {
            departmentIdMap.put(hdfsDepartmentEntity.getHdfsDepartmentId(), hdfsDepartmentEntity.getName());
        }

        List<HdfsDepartmentDbEntity> hdfsDepartmentDbEntities = hdfsDepartmentDbDao.queryList(map);
        for(int i = 0; i < hdfsDepartmentDbEntities.size(); i++) {
            HdfsDepartmentDbEntity hdfsDepartmentDbEntity = hdfsDepartmentDbEntities.get(i);
            hdfsDepartmentDbEntity.setDepartmentName(departmentIdMap.containsKey(hdfsDepartmentDbEntity.getDepartmentId()) ? departmentIdMap.get(hdfsDepartmentDbEntity.getDepartmentId()) : "");
            hdfsDepartmentDbEntities.set(i, hdfsDepartmentDbEntity);
        }
        return hdfsDepartmentDbEntities;
    }

    @Override
    public void addDepartDiskStatis() {
        List<HdfsDepartmentEntity> hdfsDepartmentEntities = hdfsDepartmentService.queryList(new HashMap<>());
        if(hdfsDepartmentEntities.isEmpty()) {
            return;
        }

        for(HdfsDepartmentEntity hdfsDepartmentEntity: hdfsDepartmentEntities) {
            Long hdfsDepartmentId = hdfsDepartmentEntity.getHdfsDepartmentId();
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("departmentId", hdfsDepartmentId);
            List<HdfsDepartmentDbEntity> hdfsDepartmentDbEntities = hdfsDepartmentDbDao.queryList(paramMap);
            if(hdfsDepartmentDbEntities.isEmpty()) {
                continue;
            }
            Long diskSum = 0L;
            for(HdfsDepartmentDbEntity hdfsDepartmentDbEntity1: hdfsDepartmentDbEntities) {
                HdfsDbEntity hdfsDbEntity = hdfsDbStatisService.queryByDbName(hdfsDepartmentDbEntity1.getDbName());
                if(hdfsDbEntity == null) {
                    continue;
                }
                diskSum += hdfsDbEntity.getDbSize();
            }
            hdfsDepartmentEntity.setDisk(diskSum);
            hdfsDepartmentService.update(hdfsDepartmentEntity);
        }
    }

    @Override
    public int queryTotal(Map<String, Object> map){
        return hdfsDepartmentDbDao.queryTotal(map);
    }

    @Override
    public void save(HdfsDepartmentDbEntity hdfsDepartmentDb){
        hdfsDepartmentDbDao.save(hdfsDepartmentDb);
    }

    @Override
    public void update(HdfsDepartmentDbEntity hdfsDepartmentDb){
        hdfsDepartmentDbDao.update(hdfsDepartmentDb);
    }

    @Override
    public void deleteBatch(Long[] hdfsDepartmentDbIds){
        hdfsDepartmentDbDao.deleteBatch(hdfsDepartmentDbIds);
    }

    @Override
    public HdfsDepartmentDbEntity queryObject(Long hdfsDepartmentDbId) {
        return hdfsDepartmentDbDao.queryObject(hdfsDepartmentDbId);
    }

}

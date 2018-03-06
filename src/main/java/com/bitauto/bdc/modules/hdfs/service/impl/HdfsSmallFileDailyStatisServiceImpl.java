package com.bitauto.bdc.modules.hdfs.service.impl;

import com.bitauto.bdc.modules.hdfs.dao.HdfsSmallFileDailyStatisDao;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileDailyStatisEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileDailyStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/28.
 */
@Service("HdfsSmallFileDailyStatisService")
public class HdfsSmallFileDailyStatisServiceImpl implements HdfsSmallFileDailyStatisService {

    @Autowired
    private HdfsSmallFileDailyStatisDao hdfsSmallFileDailyStatisDao;

    @Override
    public List<HdfsSmallFileDailyStatisEntity> queryList(Map<String, Object> map) {
        return hdfsSmallFileDailyStatisDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return hdfsSmallFileDailyStatisDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void saveAndExpire(HdfsSmallFileDailyStatisEntity hdfsSmallFileDailyStatisEntity) {
        hdfsSmallFileDailyStatisDao.updateNewestByDate(hdfsSmallFileDailyStatisEntity.getCreateDate());
        hdfsSmallFileDailyStatisDao.save(hdfsSmallFileDailyStatisEntity);
    }
}

package com.bitauto.bdc.modules.hdfs.dao;

import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by weiyongxu on 2017/10/23.
 */
@Mapper
public interface HdfsMonitorItemDao extends BaseDao<HdfsMonitorItemEntity> {
    int updateNewestByDate(String createDate);
    HdfsMonitorItemEntity queryNewestDiskByDate(String createDate);
    HdfsMonitorItemEntity queryOldestDiskByDate(String createDate);
    int updateByPrimaryKeySelective(HdfsMonitorItemEntity hdfsMonitorItemEntity);
}

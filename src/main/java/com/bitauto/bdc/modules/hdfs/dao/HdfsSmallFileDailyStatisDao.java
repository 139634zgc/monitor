package com.bitauto.bdc.modules.hdfs.dao;

import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileDailyStatisEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by weiyongxu on 2017/11/28.
 */
@Mapper
public interface HdfsSmallFileDailyStatisDao extends BaseDao<HdfsSmallFileDailyStatisEntity> {
    int updateNewestByDate(String createDate);
}

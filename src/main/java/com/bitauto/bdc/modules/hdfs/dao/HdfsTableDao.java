package com.bitauto.bdc.modules.hdfs.dao;

import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by weiyongxu on 2017/11/30.
 */
@Mapper
public interface HdfsTableDao extends BaseDao<HdfsTableEntity> {
    List<HdfsTableEntity> queryDbList();
}

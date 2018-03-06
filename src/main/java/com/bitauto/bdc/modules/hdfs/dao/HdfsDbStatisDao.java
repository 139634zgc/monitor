package com.bitauto.bdc.modules.hdfs.dao;

import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/12/5.
 */
@Mapper
public interface HdfsDbStatisDao extends BaseDao<HdfsDbEntity> {
    HdfsDbEntity queryByDbnameAndDate(Map<String, Object> map);
    int updateNewestByDbnameAndDate(Map<String, Object> map);
    List<HdfsDbEntity> listDiskIncreaseTop(Map<String, Object> map);
    List<HdfsDbEntity> listDiskTop(Map<String, Object> map);
    List<HdfsDbEntity> listDiskTopByDateRange(Map<String, Object> map);
    HdfsDbEntity queryByDbName(String dbName);
}

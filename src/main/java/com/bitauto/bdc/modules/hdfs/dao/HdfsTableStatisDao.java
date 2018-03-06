package com.bitauto.bdc.modules.hdfs.dao;

import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/30.
 */
@Mapper
public interface HdfsTableStatisDao extends BaseDao<HdfsTableEntity> {
    HdfsTableEntity queryByTableAndDate(Map<String, Object> map);
    int updateNewestByTableAndDate(Map<String, Object> map);
    List<HdfsTableEntity> queryList(Map<String, Object> map);
    List<HdfsTableEntity> listDiskIncreaseTop(Map<String, Object> map);
    List<HdfsTableEntity> listDiskTop(Map<String, Object> map);
    List<HdfsTableEntity> listDiskTopByDateRange(Map<String, Object> map);
    Long queryDbSize(Map<String, Object> map);

}

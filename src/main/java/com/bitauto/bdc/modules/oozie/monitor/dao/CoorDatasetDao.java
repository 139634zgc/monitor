package com.bitauto.bdc.modules.oozie.monitor.dao;

import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-16 13:21:53
 */
@Mapper
public interface CoorDatasetDao extends BaseDao<CoorDatasetEntity> {

    List<CoorDatasetEntity> getCoorDatasetEntity(String dataset);

    List<CoorDatasetEntity> getCoorDatasetEntityByDataSet();

    List<CoorDatasetEntity> getCoorDatesetEntityByWfId(String wfId);
}

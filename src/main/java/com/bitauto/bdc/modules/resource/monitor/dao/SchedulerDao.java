package com.bitauto.bdc.modules.resource.monitor.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.resource.monitor.entity.SchedulerEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;


/**
 * 资源调度情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
@Mapper
public interface SchedulerDao extends BaseDao<SchedulerEntity>
{
    List<SchedulerEntity> queueMonitor(Map<String, Object> params);
    void deleteByInsertTime(String oldTime);
}

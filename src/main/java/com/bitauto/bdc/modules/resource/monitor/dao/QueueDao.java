package com.bitauto.bdc.modules.resource.monitor.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.resource.monitor.entity.QueueEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;


/**
 * 资源队列常量
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:32
 */
@Mapper
public interface QueueDao extends BaseDao<QueueEntity>
{
    QueueEntity queryObjectByName(Object name);
    
    List<String> leafQueryList(Map<String, Object> map);
}

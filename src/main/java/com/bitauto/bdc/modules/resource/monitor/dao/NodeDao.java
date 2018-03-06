package com.bitauto.bdc.modules.resource.monitor.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.resource.monitor.entity.NodeEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;

/**
 * 计算节点使用情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
@Mapper
public interface NodeDao extends BaseDao<NodeEntity> {
	
}

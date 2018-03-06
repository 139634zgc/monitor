package com.bitauto.bdc.modules.resource.monitor.service;

import com.bitauto.bdc.modules.resource.monitor.entity.QueueEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源队列常量
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:32
 */
public interface QueueService {
	
	QueueEntity queryObject(Integer id);
	
	List<QueueEntity> queryList(Map<String, Object> map);
	
	List<String> leafQueryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(QueueEntity queue);
	
	void update(QueueEntity queue);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}

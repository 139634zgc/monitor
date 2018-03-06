package com.bitauto.bdc.modules.resource.monitor.service;

import com.bitauto.bdc.modules.resource.monitor.entity.QueueResultEntity;
import com.bitauto.bdc.modules.resource.monitor.entity.SchedulerEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源调度情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
public interface SchedulerService {
	
	SchedulerEntity queryObject(Integer id);
	
	 @SuppressWarnings("rawtypes")
    Map<String, Map<String, List[]>> queryList(String timeType);
	 
	 Map<String, QueueResultEntity> queueMonitor(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SchedulerEntity scheduler);
	
	void update(SchedulerEntity scheduler);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void deleteByInsertTime();
	
	/**
	 * Description: 抓取调度信息
	 * 
	 * @param url 获取此数据的url
	 * @return 是否抓取成功
	 * @see
	 */
	boolean fetchScheduler(String url);
	
	/**
     * Description: 抓取调度信息
     * 
     * @param map
     *            参数列表（insertTime,queueName,showCount）
     * @return 是否抓取成功
     * @see
     */
    List<YarnApplicationEntity> fetchTaskList(Map<String, Object> map);
    
    /**
     * Description: 抓取调度任务总条数信息
     * 
     * @param map
     *            参数列表（insertTime,queueName,showCount）
     * @return 是否抓取成功
     * @see
     */
    int fetchTaskTotal(Map<String, Object> map);
}

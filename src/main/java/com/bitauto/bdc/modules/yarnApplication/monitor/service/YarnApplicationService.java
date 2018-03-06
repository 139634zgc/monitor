package com.bitauto.bdc.modules.yarnApplication.monitor.service;

import com.bitauto.bdc.modules.yarnApplication.monitor.entity.QueueTaskCountVO;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.TaskDependentEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.TaskListEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-24 12:11:41
 */
public interface YarnApplicationService {
	
	YarnApplicationEntity queryObject(String id);
	
	List<YarnApplicationEntity> queryList(Map<String, Object> map);

	List<YarnApplicationEntity> queryListbyState(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YarnApplicationEntity yarnApplication);

	void saveBatch(List<YarnApplicationEntity> list);
	
	void update(YarnApplicationEntity yarnApplication);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);

	List<TaskListEntity> getQueneList(Map<String, Object> map);

	List<TaskListEntity> getUserTaskList(Map<String, Object> map);

	List<YarnApplicationEntity> getRunnigTask(String state);

	QueueTaskCountVO getQueneRunning();

	List<TaskDependentEntity> getTaskDependentTree();

	List<TaskDependentEntity> getTaskDependentTree(String wfId);


	TaskDependentEntity buildTaskDependentEntity(String  id);




}

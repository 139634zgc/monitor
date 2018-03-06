package com.bitauto.bdc.modules.oozie.monitor.service;


import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
public interface WorkflowsService {
	
	WorkflowsEntity queryObject(Long id);
	
	List<WorkflowsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkflowsEntity workflows);

	void saveBatch(List<WorkflowsEntity> list);
	
	void update(WorkflowsEntity workflows);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<WorkflowsEntity> getDoneFlagWf();

	List<WorkflowsEntity> getDoneFlagWfById(String wfI);

	List<WorkflowsEntity> getDoneFlagWfByName(String wfName);

	List<WorkflowsEntity> getWfByDoneFlag(String doneFlage);

	WorkflowsEntity queryWorkflowsEntity(String wfId);
}

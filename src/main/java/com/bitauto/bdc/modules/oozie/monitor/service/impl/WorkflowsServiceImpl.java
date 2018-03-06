package com.bitauto.bdc.modules.oozie.monitor.service.impl;

import com.bitauto.bdc.modules.oozie.monitor.dao.WorkflowsDao;
import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.WorkflowsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("workflowsService")
public class WorkflowsServiceImpl implements WorkflowsService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WorkflowsDao workflowsDao;
	
	@Override
	public WorkflowsEntity queryObject(Long id){
		return workflowsDao.queryObject(id);
	}
	
	@Override
	public List<WorkflowsEntity> queryList(Map<String, Object> map){
		return workflowsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workflowsDao.queryTotal(map);
	}
	
	@Override
	public void save(WorkflowsEntity workflows){
		workflowsDao.save(workflows);
	}

	@Override
	public void saveBatch(List<WorkflowsEntity> list) {
		//此处不能用批量插入的方法，批量插入方法有一个值失败就会导致整个sql 失败
		//		workflowsDao.saveBatch(list);

		for (WorkflowsEntity workflowsEntity:list) {
			try {
				workflowsDao.save(workflowsEntity);

			}catch (Exception e){
				logger.error("WorkflowsEntity save failed ,id:+"+workflowsEntity.getId(),e);
			}
		}
	}

	@Override
	public void update(WorkflowsEntity workflows){
		workflowsDao.update(workflows);
	}
	
	@Override
	public void delete(Long id){
		workflowsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		workflowsDao.deleteBatch(ids);
	}

	@Override
	public List<WorkflowsEntity> getDoneFlagWf() {
		//获取所有 生成成功标识的 wf, 凡是生成了 成功标识的wf 都有可能成为依赖数中的一个节点
		return workflowsDao.getDoneFlagWf();
	}

	@Override
	public List<WorkflowsEntity> getDoneFlagWfById(String wfI) {
		return workflowsDao.getDoneFlagWfById(wfI);
	}

	@Override
	public List<WorkflowsEntity> getDoneFlagWfByName(String wfName) {

		return null;
	}

	@Override
	public List<WorkflowsEntity> getWfByDoneFlag(String doneFlage) {
		return workflowsDao.getWfByDoneFlag(doneFlage);
	}

	@Override
	public WorkflowsEntity queryWorkflowsEntity(String wfId) {
		return workflowsDao.queryWorkflowsEntity(wfId);
	}

}

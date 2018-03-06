package com.bitauto.bdc.modules.yarnApplication.monitor.service.impl;

import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoorDatasetService;
import com.bitauto.bdc.modules.oozie.monitor.service.CoordinatorjobsService;
import com.bitauto.bdc.modules.oozie.monitor.service.WorkflowsService;
import com.bitauto.bdc.modules.yarnApplication.monitor.dao.YarnApplicationDao;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.*;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("yarnApplicationService")
public class YarnApplicationServiceImpl implements YarnApplicationService {

	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private YarnApplicationDao yarnApplicationDao;


	@Autowired
	private WorkflowsService workflowsService;

	@Autowired
	private CoorDatasetService coorDatasetService;

	@Autowired
	private CoordinatorjobsService coordinatorjobsService;
	
	@Override
	public YarnApplicationEntity queryObject(String id){
		return yarnApplicationDao.queryObject(id);
	}
	
	@Override
	public List<YarnApplicationEntity> queryList(Map<String, Object> map){
		return yarnApplicationDao.queryList(map);
	}

	@Override
	public List<YarnApplicationEntity> queryListbyState(Map<String, Object> map) {
		return yarnApplicationDao.queryListbyState(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return yarnApplicationDao.queryTotal(map);
	}
	
	@Override
	public void save(YarnApplicationEntity yarnApplication){
		yarnApplicationDao.save(yarnApplication);
	}

	@Override
	public void saveBatch(List<YarnApplicationEntity> list) {
		for (YarnApplicationEntity entity: list) {
			try {
				yarnApplicationDao.save(entity);
			}catch (Exception e){
				logger.error("YarnApplicationEntity save failed ,id:+"+entity.getId(),e);
			}
		}
	}

	@Override
	public void update(YarnApplicationEntity yarnApplication){
		if(null != yarnApplication){
			yarnApplicationDao.update(yarnApplication);
		}
	}
	
	@Override
	public void delete(String id){
		yarnApplicationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		yarnApplicationDao.deleteBatch(ids);
	}

	@Override
	public List<TaskListEntity> getQueneList(Map<String, Object> map) {
		List<String> queneList = yarnApplicationDao.getQueneList();
		List<TaskListEntity> resultList = new ArrayList<>();
		List<StatisticsEntity> list = yarnApplicationDao.getCountByQueneAndState();

		queneList.forEach(quene -> {
			TaskListEntity entity = new TaskListEntity();
			entity.setQueneName(quene);
			int failNum = this.getCountByState(quene,list,"KILLED") +this.getCountByState(quene,list,"FAILED");
			entity.setFailNum(failNum);
			entity.setSuccessNum(this.getCountByState(quene,list,"FINISHED"));
			entity.setTaskNum(yarnApplicationDao.getCountByQuene(quene));
			resultList.add(entity);
		});
		return resultList;
	}


	private int getCountByState(List<StatisticsEntity> list,String status){
		int num = 0;
		for (StatisticsEntity statisticsEntity:list) {
			if(statisticsEntity.getState().equalsIgnoreCase(status)){
				num = statisticsEntity.getCount();
			}
		}
		return num;
	}

	private int getCountByState(String name,List<StatisticsEntity> list,String status){
		int num = 0;
		for (StatisticsEntity statisticsEntity:list) {
			if(statisticsEntity.getState().equalsIgnoreCase(status) && name.equals(statisticsEntity.getName())){
				num = statisticsEntity.getCount();
			}
		}
		return num;
	}




	@Override
	public List<TaskListEntity> getUserTaskList(Map<String, Object> map) {
		List<StatisticsEntity> statisticsEntityList = yarnApplicationDao.getCountByUser(map);

		List<TaskListEntity> taskListEntityList = new ArrayList<>();
		List<StatisticsEntity> list = yarnApplicationDao.getCountByUserAndState();


		statisticsEntityList.forEach(statisticsEntity -> {
			TaskListEntity entity = new TaskListEntity();
			entity.setTaskNum(statisticsEntity.getCount());
			entity.setUserName(statisticsEntity.getName());
			int failNum = this.getCountByState(statisticsEntity.getName(),list,"KILLED")+this.getCountByState(statisticsEntity.getName(),list,"FAILED");
			entity.setFailNum(failNum);
			entity.setSuccessNum(this.getCountByState(statisticsEntity.getName(),list,"FINISHED"));

			taskListEntityList.add(entity);
		});

		return taskListEntityList;
	}

	@Override
	public List<YarnApplicationEntity> getRunnigTask(String state){
		return yarnApplicationDao.getRunnigTask(state);
	}

	@Override
	public QueueTaskCountVO getQueneRunning() {

		List<String> queneList = yarnApplicationDao.getQueneList();
		List<StatisticsEntity> runninglist = yarnApplicationDao.getQueueTaskCountByState("RUNNING");
		List<StatisticsEntity> pendinglist = yarnApplicationDao.getQueueTaskCountByState("ACCEPTED");
		QueueTaskCountVO vo = new QueueTaskCountVO();

		Map<String,List<Integer>> map = new HashMap<>();
		map.put("running",buildQueueData(runninglist,queneList));
		map.put("pending",buildQueueData(pendinglist,queneList));

		vo.setData(map);
		vo.setQueue(queneList);
		return vo;
	}

	@Override
	public List<TaskDependentEntity> getTaskDependentTree() {
		List<TaskDependentEntity> taskDependentEntityList= new ArrayList<>();

		List<WorkflowsEntity>  list =workflowsService.getDoneFlagWf();
		for (WorkflowsEntity workflowsEntity: list) {
			TaskDependentEntity taskDependentEntity = buildTaskDependentEntity(workflowsEntity.getId());
			taskDependentEntityList.add(taskDependentEntity);
		}

		return taskDependentEntityList;
	}




	@Override
	public List<TaskDependentEntity> getTaskDependentTree(String param) {
		List<TaskDependentEntity> taskDependentEntityList= new ArrayList<>();

		Set<WorkflowsEntity> set =new  HashSet<>();

		List<WorkflowsEntity> listName = workflowsService.getDoneFlagWfByName(param);
		List<WorkflowsEntity>  listwfId =workflowsService.getDoneFlagWfById(param);

		//根据name 和 id 的结果去重
		if(null != listName){
			set.addAll(listName);

		}
		if(null != listwfId){
			set.addAll(listwfId);
		}
		for (WorkflowsEntity workflowsEntity: set) {
			TaskDependentEntity taskDependentEntity = buildTaskDependentEntity(workflowsEntity.getId());
			taskDependentEntityList.add(taskDependentEntity);
		}

		return taskDependentEntityList;
	}

//	private TaskDependentEntity buildTaskDependentEntityDemo(String wfId){
//		TaskDependentEntity entity = new TaskDependentEntity();
//		WorkflowsEntity workflowsEntity = this.workflowsService.queryWorkflowsEntity(wfId);
//
//		entity.setName(workflowsEntity.getAppname());
//		entity.setValue(workflowsEntity.getUser());
//
//		List<CoorDatasetEntity> coorDatasetEntities
//				= coorDatasetService.getCoorDatasetEntity(workflowsEntity.getDoneflag());
//		List<TaskDependentEntity> list= new ArrayList<>();
//
//		for (CoorDatasetEntity coorDatasetEntity:coorDatasetEntities) {
//			TaskDependentEntity taskDependentEntity1 = new TaskDependentEntity();
//			taskDependentEntity1.setName(coorDatasetEntity.getCoordjobname());
//			taskDependentEntity1.setValue(coorDatasetEntity.getWfId());
//			list.add(taskDependentEntity1);
//		}
//		entity.setChildren(list);
//
//		return entity;
//
//	}



	public TaskDependentEntity buildTaskDependentEntity(String  id){

		WorkflowsEntity workflowsEntity = this.workflowsService.queryWorkflowsEntity(id);
		if(workflowsEntity == null || StringUtils.isEmpty(workflowsEntity.getDoneflag())){
			return null;
		}

		TaskDependentEntity taskDependentEntity = new TaskDependentEntity();
		taskDependentEntity.setName(workflowsEntity.getAppname());
		taskDependentEntity.setValue(workflowsEntity.getUser());

		List<CoorDatasetEntity> coorDatasetEntities
				= coorDatasetService.getCoorDatasetEntity(workflowsEntity.getDoneflag());

		List<TaskDependentEntity> list= new ArrayList<>();
		for (CoorDatasetEntity coorDatasetEntity:coorDatasetEntities) {
			if(!StringUtils.isBlank(coorDatasetEntity.getDataset())){
				list.add(buildTaskDependentEntity(coorDatasetEntity.getWfId()));
			}
		}
		taskDependentEntity.setChildren(list);

		return taskDependentEntity;

	}


	private List<Integer> buildQueueData(List<StatisticsEntity> list,List<String> queneList ){
		List<Integer> res = new ArrayList<>();
		queneList.forEach(quene->{
			for (StatisticsEntity statisticsEntity:list) {
				if(quene.equalsIgnoreCase(statisticsEntity.getName())){
					res.add(statisticsEntity.getCount());
				}
			}
		});
		return res;
	}


}

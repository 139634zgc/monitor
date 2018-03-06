package com.bitauto.bdc.modules.oozie.monitor.service.impl;

import com.bitauto.bdc.modules.oozie.monitor.dao.CoordinatorjobsDao;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoordinatorjobsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Service("coordinatorjobsService")
public class CoordinatorjobsServiceImpl implements CoordinatorjobsService {

	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private CoordinatorjobsDao coordinatorjobsDao;
	
	@Override
	public CoordinatorjobsEntity queryObject(Long id){
		return coordinatorjobsDao.queryObject(id);
	}
	
	@Override
	public List<CoordinatorjobsEntity> queryList(Map<String, Object> map){
		return coordinatorjobsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return coordinatorjobsDao.queryTotal(map);
	}
	
	@Override
	public void save(CoordinatorjobsEntity coordinatorjobs){
		coordinatorjobsDao.save(coordinatorjobs);
	}

	@Override
	public void saveBatch(List<CoordinatorjobsEntity> list) {
//		if(null !=list && list.size() >0){
//			coordinatorjobsDao.saveBatch(list);
//		}
		for (CoordinatorjobsEntity entity : list) {
			try {
				coordinatorjobsDao.save(entity);
			}catch (Exception e){
				logger.error("CoordinatorjobsEntity save failed ,id:+"+entity.getCoordjobid(),e);

			}
		}
	}

	@Override
	public void update(CoordinatorjobsEntity coordinatorjobs){
		coordinatorjobsDao.update(coordinatorjobs);
	}
	
	@Override
	public void delete(Long id){
		coordinatorjobsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		coordinatorjobsDao.deleteBatch(ids);
	}

	@Override
	public List<CoordinatorjobsEntity> queryListByIds(Set<String> ids) {
		if(null == ids || ids.size()<1){
			return null;
		}
		return coordinatorjobsDao.queryListByIds(ids);
	}

	@Override
	public List<CoordinatorjobsEntity> queryListByCoorId(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		return this.coordinatorjobsDao.queryListByCoorId(id);
	}

}

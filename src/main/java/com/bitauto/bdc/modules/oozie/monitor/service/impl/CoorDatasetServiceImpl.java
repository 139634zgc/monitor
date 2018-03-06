package com.bitauto.bdc.modules.oozie.monitor.service.impl;

import com.bitauto.bdc.modules.oozie.monitor.dao.CoorDatasetDao;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoorDatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("coorDatasetService")
public class CoorDatasetServiceImpl implements CoorDatasetService {
	@Autowired
	private CoorDatasetDao coorDatasetDao;
	
	@Override
	public CoorDatasetEntity queryObject(String coordinatorjobid){
		return coorDatasetDao.queryObject(coordinatorjobid);
	}
	
	@Override
	public List<CoorDatasetEntity> queryList(Map<String, Object> map){
		return coorDatasetDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return coorDatasetDao.queryTotal(map);
	}
	
	@Override
	public void save(CoorDatasetEntity coorDataset){
		coorDatasetDao.save(coorDataset);
	}

	@Override
	public void saveBatch(List<CoorDatasetEntity> list) {
		coorDatasetDao.saveBatch(list);
	}

	@Override
	public void update(CoorDatasetEntity coorDataset){
		coorDatasetDao.update(coorDataset);
	}
	
	@Override
	public void delete(String coordinatorjobid){
		coorDatasetDao.delete(coordinatorjobid);
	}
	
	@Override
	public void deleteBatch(String[] coordinatorjobids){
		coorDatasetDao.deleteBatch(coordinatorjobids);
	}

	@Override
	public List<CoorDatasetEntity> getCoorDatasetEntity(String dataset) {
		return coorDatasetDao.getCoorDatasetEntity(dataset);
	}

	@Override
	public List<CoorDatasetEntity> getCoorDatasetEntityByDataSet() {
		return  getCoorDatasetEntityByDataSet();
	}

	@Override
	public List<CoorDatasetEntity> getCoorDatesetEntityByWfId(String wfId) {
		return coorDatasetDao.getCoorDatesetEntityByWfId(wfId);
	}


}

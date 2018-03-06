package com.bitauto.bdc.modules.oozie.monitor.service;


import com.bitauto.bdc.modules.oozie.monitor.entity.CoorDatasetEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-16 13:21:53
 */
public interface CoorDatasetService {
	
	CoorDatasetEntity queryObject(String coordinatorjobid);
	
	List<CoorDatasetEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CoorDatasetEntity coorDataset);

	void saveBatch(List<CoorDatasetEntity>  list);
	
	void update(CoorDatasetEntity coorDataset);
	
	void delete(String coordinatorjobid);
	
	void deleteBatch(String[] coordinatorjobids);

	List<CoorDatasetEntity> getCoorDatasetEntity(String dataset);

	List<CoorDatasetEntity> getCoorDatasetEntityByDataSet();

	List<CoorDatasetEntity> getCoorDatesetEntityByWfId(String wfId);
}

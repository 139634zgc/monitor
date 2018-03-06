package com.bitauto.bdc.modules.oozie.monitor.service;


import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
public interface CoordinatorjobsService {
	
	CoordinatorjobsEntity queryObject(Long id);
	
	List<CoordinatorjobsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CoordinatorjobsEntity coordinatorjobs);

	void saveBatch(List<CoordinatorjobsEntity> list);
	
	void update(CoordinatorjobsEntity coordinatorjobs);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<CoordinatorjobsEntity> queryListByIds(Set<String> ids);

	List<CoordinatorjobsEntity> queryListByCoorId(String id);
}

package com.bitauto.bdc.modules.config.service;


import com.bitauto.bdc.modules.config.entity.ConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-03 10:09:06
 */
public interface ConfigService {
	
	ConfigEntity queryObject(Long id);
	
	List<ConfigEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ConfigEntity config);
	
	void update(ConfigEntity config);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	ConfigEntity queryBykey(String key);

	void updateBykey(String key,String value);
}

package com.bitauto.bdc.modules.config.service.impl;

import com.bitauto.bdc.modules.config.dao.ConfigDao;
import com.bitauto.bdc.modules.config.entity.ConfigEntity;
import com.bitauto.bdc.modules.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;
	
	@Override
	public ConfigEntity queryObject(Long id){
		return configDao.queryObject(id);
	}
	
	@Override
	public List<ConfigEntity> queryList(Map<String, Object> map){
		return configDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return configDao.queryTotal(map);
	}
	
	@Override
	public void save(ConfigEntity config){
		configDao.save(config);
	}
	
	@Override
	public void update(ConfigEntity config){
		configDao.update(config);
	}
	
	@Override
	public void delete(Long id){
		configDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		configDao.deleteBatch(ids);
	}

	@Override
	public ConfigEntity queryBykey(String key) {
		return configDao.queryBykey(key);
	}

	@Override
	public void updateBykey(String key, String value) {
		ConfigEntity entity = new ConfigEntity();
		entity.setKey(key);
		entity.setValue(value);
		configDao.updateBykey(entity);
	}


}

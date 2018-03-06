package com.bitauto.bdc.modules.resource.monitor.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitauto.bdc.modules.resource.monitor.dao.QueueDao;
import com.bitauto.bdc.modules.resource.monitor.entity.QueueEntity;
import com.bitauto.bdc.modules.resource.monitor.service.QueueService;



@Service("queueService")
public class QueueServiceImpl implements QueueService {
	@Autowired
	private QueueDao queueDao;
	
	@Override
	public QueueEntity queryObject(Integer id){
		return queueDao.queryObject(id);
	}
	
	@Override
	public List<QueueEntity> queryList(Map<String, Object> map){
		return queueDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return queueDao.queryTotal(map);
	}
	
	@Override
	public void save(QueueEntity queue){
		queueDao.save(queue);
	}
	
	@Override
	public void update(QueueEntity queue){
		queueDao.update(queue);
	}
	
	@Override
	public void delete(Integer id){
		queueDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		queueDao.deleteBatch(ids);
	}

    @Override
    public List<String> leafQueryList(Map<String, Object> map)
    {
        return queueDao.leafQueryList(map);
    }
	
}

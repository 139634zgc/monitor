package com.bitauto.bdc.modules.resource.monitor.service;

import com.bitauto.bdc.modules.resource.monitor.entity.NodeEntity;

import java.util.List;
import java.util.Map;

/**
 * 计算节点使用情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
public interface NodeService {
	
	NodeEntity queryObject(Integer id);
	
	List<NodeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(NodeEntity node);
	
	void update(NodeEntity node);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
     * Description: 抓取调度信息
     * 
     * @param url 获取此数据的url
     * @return 是否抓取成功
     * @see
     */
    boolean fetchNode(String url);
}

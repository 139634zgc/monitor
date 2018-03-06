package com.bitauto.bdc.modules.resource.monitor.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bitauto.bdc.common.utils.DataCheck;
import com.bitauto.bdc.common.utils.HttpUtil;
import com.bitauto.bdc.modules.resource.monitor.dao.NodeDao;
import com.bitauto.bdc.modules.resource.monitor.entity.NodeEntity;
import com.bitauto.bdc.modules.resource.monitor.service.NodeService;


@Service("nodeService")
public class NodeServiceImpl implements NodeService
{
    
    /**
     * 日志定义
     */
    private Logger LOG = LoggerFactory.getLogger(NodeServiceImpl.class);
    
    private static final String URL = "http://192.168.15.46:8088/ws/v1/cluster/nodes";
    
    @Autowired
    private NodeDao nodeDao;
    
    @Override
    public NodeEntity queryObject(Integer id)
    {
        return nodeDao.queryObject(id);
    }
    
    @Override
    public List<NodeEntity> queryList(Map<String, Object> map)
    {
        return nodeDao.queryList(map);
    }
    
    @Override
    public int queryTotal(Map<String, Object> map)
    {
        return nodeDao.queryTotal(map);
    }
    
    @Override
    public void save(NodeEntity node)
    {
        nodeDao.save(node);
    }
    
    @Override
    public void update(NodeEntity node)
    {
        nodeDao.update(node);
    }
    
    @Override
    public void delete(Integer id)
    {
        nodeDao.delete(id);
    }
    
    @Override
    public void deleteBatch(Integer[] ids)
    {
        nodeDao.deleteBatch(ids);
    }
    
    public static void main(String[] args)
    {   
        new NodeServiceImpl().fetchNode(URL);
    }
    
    /**
     * 抓取nodemanager节点
     */
    @Override
    public boolean fetchNode(String url)
    {
        String nodeInfo = HttpUtil.requestHttp(url);
        
        Date insertTime = new Date();
        
        // 是否抓取调度信息异常
        if (StringUtils.isEmpty(nodeInfo))
        {
            LOG.error("Get nodes content failed， pls check server whether connected.");
            return false;
        }
        
        try
        {
            JSONArray nodes = JSON.parseArray(JSON.parseObject(JSON.parseObject(nodeInfo).get("nodes").toString()).get("node").toString());
            List<NodeEntity> nodeList = new ArrayList<NodeEntity>();
            
            for (Object node : nodes)
            {
                nodeList.add(parseNode(node, insertTime));
            }
            
            // 将处理的队列信息插入到数据库
            if (DataCheck.checkList(nodeList))
            {
                for (NodeEntity nodeEntity : nodeList)
                {
                    save(nodeEntity);
                    System.out.println(nodeEntity.toString());
                }
            }
        }
        catch (Exception e)
        {
            LOG.error(
                "History job parse failed, the content is: " + nodeInfo.substring(0, 100)
                    + "..., the exception[{}]", e);
            return false;
        }
        return false;
    }
    
    /**
     * Description:
     * 解析调度器，此解析有可能是root队列，也有可能是child队列
     * 
     * @param jsonObj
     *            将要被解析的对象
     * @param inserTime
     *            数据插入时间(此处需要该字段主要为了保证同一批次插入数据的时间一致)
     * @return 资源调度对象
     * @see
     */
    private NodeEntity parseNode(Object nodeObj, Date insertTime)
        throws Exception
    {
        if (null == nodeObj)
        {
            LOG.error("Parse obj is null.");
            return null;
        }
        
        NodeEntity nodeEntity = JSON.parseObject(nodeObj.toString(), NodeEntity.class);
        nodeEntity.setInserttime(insertTime);
        
        return nodeEntity;
    }
}

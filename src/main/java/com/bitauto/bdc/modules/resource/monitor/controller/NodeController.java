package com.bitauto.bdc.modules.resource.monitor.controller;


import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.resource.monitor.entity.NodeEntity;
import com.bitauto.bdc.modules.resource.monitor.service.NodeService;


/**
 * 计算节点使用情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
@RestController
@RequestMapping("/monitor/node")
public class NodeController
{
    @Autowired
    private NodeService nodeService;
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("monitor:node:list")
    public R list(@RequestParam Map<String, Object> params)
    {
        // 查询列表数据
        Query query = new Query(params);
        
        List<NodeEntity> nodeList = nodeService.queryList(query);
        int total = nodeService.queryTotal(query);
        
        PageUtils pageUtil = new PageUtils(nodeList, total, query.getLimit(), query.getPage());
        
        return R.ok().put("page", pageUtil);
    }
    
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("monitor:node:info")
    public R info(@PathVariable("id") Integer id)
    {
        NodeEntity node = nodeService.queryObject(id);
        
        return R.ok().put("node", node);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("monitor:node:save")
    public R save(@RequestBody NodeEntity node)
    {
        nodeService.save(node);
        
        return R.ok();
    }
    
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("monitor:node:update")
    public R update(@RequestBody NodeEntity node)
    {
        nodeService.update(node);
        
        return R.ok();
    }
    
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("monitor:node:delete")
    public R delete(@RequestBody Integer[] ids)
    {
        nodeService.deleteBatch(ids);
        
        return R.ok();
    }
    
}

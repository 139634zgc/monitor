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
import com.bitauto.bdc.modules.resource.monitor.entity.QueueEntity;
import com.bitauto.bdc.modules.resource.monitor.service.QueueService;


/**
 * 资源队列常量
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:32
 */
@RestController
@RequestMapping("/monitor/queue")
public class QueueController
{
    @Autowired
    private QueueService queueService;
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("monitor:queue:list")
    public R list(@RequestParam Map<String, Object> params)
    {
        // 查询列表数据
        Query query = new Query(params);
        
        List<QueueEntity> queueList = queueService.queryList(query);
        int total = queueService.queryTotal(query);
        
        PageUtils pageUtil = new PageUtils(queueList, total, query.getLimit(), query.getPage());
        
        return R.ok().put("page", pageUtil);
    }
    
    /**
     * 子队列列表
     */
    @RequestMapping("/leafqueue")
    @RequiresPermissions("monitor:queue:leafqueue")
    public R leafqueue(@RequestParam Map<String, Object> params)
    {
        return R.ok().put("page", queueService.leafQueryList(params));
    }
    
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("monitor:queue:info")
    public R info(@PathVariable("id") Integer id)
    {
        QueueEntity queue = queueService.queryObject(id);
        
        return R.ok().put("queue", queue);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("monitor:queue:save")
    public R save(@RequestBody QueueEntity queue)
    {
        queueService.save(queue);
        
        return R.ok();
    }
    
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("monitor:queue:update")
    public R update(@RequestBody QueueEntity queue)
    {
        queueService.update(queue);
        
        return R.ok();
    }
    
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("monitor:queue:delete")
    public R delete(@RequestBody Integer[] ids)
    {
        queueService.deleteBatch(ids);
        
        return R.ok();
    }
    
}

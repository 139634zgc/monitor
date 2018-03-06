package com.bitauto.bdc.modules.resource.monitor.controller;


import java.util.HashMap;
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
import com.bitauto.bdc.modules.resource.monitor.entity.QueueResultEntity;
import com.bitauto.bdc.modules.resource.monitor.entity.SchedulerEntity;
import com.bitauto.bdc.modules.resource.monitor.service.QueueService;
import com.bitauto.bdc.modules.resource.monitor.service.SchedulerService;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;


/**
 * 资源调度情况
 * 
 * @author liuming
 * @email liuming1@yiche.com
 * @date 2017-10-23 10:22:33
 */
@RestController
@RequestMapping("/monitor/scheduler")
public class SchedulerController
{
    @Autowired
    private SchedulerService schedulerService;
    
    @Autowired
    private QueueService queueService;
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("monitor:scheduler:list")
    public R list(@RequestParam Map<String, Object> params)
    {
        // 查询列表数据
        String timeType = params.get("timeType").toString();
        
        return R.ok().put("page", schedulerService.queryList(timeType));
    }
    
    /**
     * Description:队列监控
     * 
     * @param params
     *            queueName
     * @return
     * @see
     */
    @RequestMapping("/queuemonitor")
    @RequiresPermissions("monitor:scheduler:queuemonitor")
    public R queuemonitor(@RequestParam Map<String, Object> params)
    {
        // 查询单个队列的信息
        Map<String, QueueResultEntity> queueMap = schedulerService.queueMonitor(params);
        
        if (null == queueMap || queueMap.isEmpty())
        {
            return R.error("No data!");
        }
        else
        {
            Map<String, Object> result = new HashMap<String, Object>();
            
            for (Map.Entry<String, QueueResultEntity> entry : queueMap.entrySet())
            {
                if (entry.getKey().equalsIgnoreCase("cpu"))
                {
                    result.put("cpuSteadyCount", entry.getValue().getContainerSize());
                    result.put("cpuResVals", entry.getValue().getValues());
                    result.put("cpuMaxRes", entry.getValue().getMaxRes());
                }
                else
                {
                    result.put("memSteadyCount", entry.getValue().getContainerSize());
                    result.put("memResVals", entry.getValue().getValues());
                    result.put("memMaxRes", entry.getValue().getMaxRes());
                }
            }
            return R.ok(result);
        }
    }
    
    /**
     * Description:获取子队列列表
     * 
     * @param params
     *            queueName
     * @return
     * @see
     */
    @RequestMapping("/queuelist")
    @RequiresPermissions("monitor:scheduler:queuelist")
    public R queuelist(@RequestParam Map<String, Object> params)
    {
        // 查询单个队列的信息
        return R.ok().put("page", queueService.leafQueryList(params));
    }
    
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("monitor:scheduler:info")
    public R info(@PathVariable("id") Integer id)
    {
        SchedulerEntity scheduler = schedulerService.queryObject(id);
        
        return R.ok().put("scheduler", scheduler);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("monitor:scheduler:save")
    public R save(@RequestBody SchedulerEntity scheduler)
    {
        schedulerService.save(scheduler);
        
        return R.ok();
    }
    
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("monitor:scheduler:update")
    public R update(@RequestBody SchedulerEntity scheduler)
    {
        schedulerService.update(scheduler);
        
        return R.ok();
    }
    
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("monitor:scheduler:delete")
    public R delete(@RequestBody Integer[] ids)
    {
        schedulerService.deleteBatch(ids);
        
        return R.ok();
    }
    
    /**
     * 列表
     */
    @RequestMapping("/tasklist")
    @RequiresPermissions("monitor:taskscheduler:list")
    public R taskList(@RequestParam Map<String, Object> params)
    {
        Query query = new Query(params);
        
        List<YarnApplicationEntity> queueList = schedulerService.fetchTaskList(query);
        // int total = schedulerService.fetchTaskTotal(query); //去掉分页功能
        int total = 0;
        
        PageUtils pageUtil = new PageUtils(queueList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
}

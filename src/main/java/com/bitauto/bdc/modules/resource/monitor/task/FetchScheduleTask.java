/*
 * 文件名：FetchScheduleTask.java
 * 版权：Copyright by www.yiche.com
 * 描述：
 * 修改人：liuming1
 * 修改时间：2017年11月3日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bitauto.bdc.modules.resource.monitor.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bitauto.bdc.modules.resource.monitor.service.SchedulerService;


@Component("scheduleFetch")
public class FetchScheduleTask
{
    /**
     * 抓取schedule的url地址信息
     */
    @Value("${RESTAPI.SHCEDULE_BASE_URL}")
    private String S_SCHEDULE_URL = "http://192.168.15.46:8088/ws/v1/cluster/scheduler";
    
    @Autowired
    private SchedulerService schedulerService;
    
    /**
     * Description: 抓取调度数据
     * 
     * @return 处理结果状态
     * @see
     */
    public void fetchSchedule()
    {
        schedulerService.fetchScheduler(S_SCHEDULE_URL);
    }
}

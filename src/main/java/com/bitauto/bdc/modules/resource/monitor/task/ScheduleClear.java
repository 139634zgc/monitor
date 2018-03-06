/*
 * 文件名：ScheduleClear.java
 * 版权：Copyright by www.yiche.com
 * 描述：
 * 修改人：liuming1
 * 修改时间：2017年12月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bitauto.bdc.modules.resource.monitor.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitauto.bdc.modules.resource.monitor.service.SchedulerService;

@Component("scheduleClear")
public class ScheduleClear
{
    @Autowired
    private SchedulerService schedulerService;
    
    /**
     * Description: 抓取调度数据
     * 
     * @return 处理结果状态
     * @see
     */
    public void clearSchedule()
    {
        schedulerService.deleteByInsertTime();
    }
}

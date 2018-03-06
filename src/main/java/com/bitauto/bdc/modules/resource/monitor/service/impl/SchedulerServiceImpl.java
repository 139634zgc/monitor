package com.bitauto.bdc.modules.resource.monitor.service.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bitauto.bdc.common.utils.DataCheck;
import com.bitauto.bdc.common.utils.HttpUtil;
import com.bitauto.bdc.modules.resource.monitor.dao.QueueDao;
import com.bitauto.bdc.modules.resource.monitor.dao.SchedulerDao;
import com.bitauto.bdc.modules.resource.monitor.entity.QueueEntity;
import com.bitauto.bdc.modules.resource.monitor.entity.SchedulerEntity;
import com.bitauto.bdc.modules.resource.monitor.entity.QueueResultEntity;
import com.bitauto.bdc.modules.resource.monitor.entity.enums.TimeTypeEnum;
import com.bitauto.bdc.modules.resource.monitor.service.SchedulerService;
import com.bitauto.bdc.modules.yarnApplication.monitor.dao.YarnApplicationDao;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;


@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService
{
    /**
     * 日志采集定义
     */
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerServiceImpl.class);
    
    /**
     * 时间格式化类
     */
    private static SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    /**
     * 任务默认显示条数
     */
    private static final int I_DEFAULT_COUNT = 10;
    
    @Autowired
    private SchedulerDao schedulerDao;
    
    @Autowired
    private QueueDao queueDao;
    
    @Autowired
    private YarnApplicationDao applicationDao;
    
    @Override
    public SchedulerEntity queryObject(Integer id)
    {
        return schedulerDao.queryObject(id);
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, Map<String, List[]>> queryList(String timeType)
    {
        Map<String, Map<String, List[]>> monitor = new HashMap<String, Map<String, List[]>>();
        List<SchedulerEntity> schedulerList = schedulerDao.queryList(getTime(timeType));
        
        // 删除父节点队列，值留下叶子队列
        List<QueueEntity> queueEntities = queueDao.queryList(null);
        List<String> leafQueueList = new ArrayList<String>();
        if (null != queueEntities)
        {
            for (QueueEntity queueEntity : queueEntities)
            {
                if (queueEntity.isLeaf())
                {
                    leafQueueList.add(queueEntity.getQueuepath());
                }
            }
        }
        else
        {
            new RuntimeException("get queue list exception, pls check queue db or code.");
        }
        
        for (SchedulerEntity scheduler : schedulerList)
        {
            // cpu 数据组装
            packageData(monitor, scheduler, "cpu", leafQueueList);
            
            // 内存组装
            packageData(monitor, scheduler, "mem", leafQueueList);
        }
        
        return monitor;
    }
    
    @Override
    public int queryTotal(Map<String, Object> map)
    {
        return schedulerDao.queryTotal(map);
    }
    
    @Override
    public void save(SchedulerEntity scheduler)
    {
        schedulerDao.save(scheduler);
    }
    
    @Override
    public void update(SchedulerEntity scheduler)
    {
        schedulerDao.update(scheduler);
    }
    
    @Override
    public void delete(Integer id)
    {
        schedulerDao.delete(id);
    }
    
    @Override
    public void deleteBatch(Integer[] ids)
    {
        schedulerDao.deleteBatch(ids);
    }
    
    @Override
    public List<YarnApplicationEntity> fetchTaskList(Map<String, Object> params)
    {
        try
        {
            if (null != params)
            {
                if (null != params.get("insertTime"))
                {
                    if (!(params.get("insertTime") instanceof Long))
                    {
                        params.put("insertTime",
                            formate.parse(params.get("insertTime").toString()).getTime());
                    }
                }
                
                if (null != params.get("showCount")
                    && !"".equals(params.get("showCount").toString()))
                {
                    params.put("showCount", Integer.valueOf(params.get("showCount").toString()));
                }
                else
                {
                    params.put("showCount", I_DEFAULT_COUNT);
                }
            }
        }
        catch (ParseException e)
        {
            LOG.error("fetchTask param error, the exception[{}]", e);
        }
        return applicationDao.queryListbyQueue(params);
    }
    
    /**
     * Description: 抓取调度任务总条数信息
     * 
     * @param map
     *            参数列表（insertTime,queueName,showCount）
     * @return 是否抓取成功
     * @see
     */
    @Override
    public int fetchTaskTotal(Map<String, Object> params)
    {
        try
        {
            if (null != params && null != params.get("insertTime"))
            {
                if (!(params.get("insertTime") instanceof Long))
                {
                    params.put("insertTime",
                        formate.parse(params.get("insertTime").toString()).getTime());
                }
            }
        }
        catch (ParseException e)
        {
            LOG.error("fetchTask param error, the exception[{}]", e);
        }
        return applicationDao.queryTotalbyQueue(params);
    }
    
    public static void main(String[] args)
    {
        String info = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(
            "C:/Users/liuming1.TECH/Desktop/小文件发现/scheduler"))))
        {
            info = br.readLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println("前:" + info);
        String scheduleInfo = new SchedulerServiceImpl().replaceKey(info, "childQueues");
        System.out.println("后:" + scheduleInfo);
        // System.out.println(JSON.parseObject(scheduleInfo).toString().contains("bdc.dw"));
        new SchedulerServiceImpl().fetchScheduler(scheduleInfo);
    }
    
    /**
     * 抓取调度信息
     */
    @Override
    public boolean fetchScheduler(String url)
    {
        String scheduleInfo = replaceKey(HttpUtil.requestHttp(url), "childQueues");
        // String scheduleInfo = url;
        Date insertTime = new Date();
        
        // 是否抓取调度信息异常
        if (StringUtils.isEmpty(scheduleInfo))
        {
            LOG.error("Get schedule content failed， pls check server whether connected.");
            return false;
        }
        
        try
        {
            JSONObject rootQueue = JSON.parseObject(JSON.parseObject(
                JSON.parseObject(JSON.parseObject(scheduleInfo).get("scheduler").toString()).get(
                    "schedulerInfo").toString()).get("rootQueue").toString());
            List<SchedulerEntity> scheduleList = new ArrayList<SchedulerEntity>();
            // JSON.parseObject("", Feature.)
            
            // 处理根队列
            scheduleList.add(parseSchedule(rootQueue, insertTime));
            
            // 递归处理子队列，当没有子队列处理时，方法返回
            JSONArray childQueues = JSON.parseArray(rootQueue.get("childQueues0").toString());
            processChildQueue(childQueues, scheduleList, insertTime);
            
            // 将处理的队列信息插入到数据库
            if (DataCheck.checkList(scheduleList))
            {
                for (SchedulerEntity schedulerEntity : scheduleList)
                {
                    save(schedulerEntity);
                }
            }
        }
        catch (Exception e)
        {
            LOG.error(
                "History job parse failed, the content is: " + scheduleInfo.substring(0, 100)
                    + "..., the exception[{}]", e);
            return false;
        }
        return false;
    }
    
    /**
     * Description: 递归处理子队列，当没有子队列之后返回方法体
     * 
     * @param childQueues
     *            子队列
     * @param scheduleList
     *            队列容器
     * @param insertTime
     *            数据插入时间
     * @throws Exception
     *             异常
     * @see
     */
    private void processChildQueue(JSONArray childQueues, List<SchedulerEntity> scheduleList,
                                   Date insertTime)
        throws Exception
    {
        // 当子队列存在的情况下，循环处理子队列
        if (null != childQueues && 0 < childQueues.size())
        {
            for (Object childQueue : childQueues)
            {
                recursiveChild(scheduleList, childQueue, insertTime);
            }
        }
    }
    
    /**
     * Description:递归处理子节点，当父队列中包含子队列时，会递归处理该父队列下的所有的子队列，如果没有子队列，则返回父队列
     * 
     * @param scheduleList
     *            资源收集容器
     * @param childQueue
     *            子队列对象
     * @param insertTime
     *            数据插入时间
     * @throws Exception
     *             解析异常时，抛出异常
     */
    private void recursiveChild(List<SchedulerEntity> scheduleList, Object childQueue,
                                Date insertTime)
        throws Exception
    {
        JSONObject jsonObject;
        jsonObject = JSON.parseObject(childQueue.toString());
        scheduleList.add(parseSchedule(jsonObject, insertTime));
        
        for (Map.Entry<String, Object> entry : jsonObject.entrySet())
        {
            if (entry.getKey().startsWith("childQueues"))
            {
                recursiveChild(scheduleList, entry.getValue(), insertTime);
            }
        }
        return;
    }
    
    /**
     * Description: 替换json字符串中的关键字符，避免json无法解析或者解析值错误的问题
     * 
     * @param jsonChar
     *            需要被处理的json字符串
     * @param findKey
     *            需要查找的关键字
     * @return 替换后的json字符串
     */
    private String replaceKey(String jsonChar, String findKey)
    {
        // 参数值校验，当参数无效时原样返回
        if (StringUtils.isEmpty(jsonChar) || StringUtils.isEmpty(findKey))
        {
            return jsonChar;
        }
        
        int matchCount = StringUtils.countMatches(jsonChar, findKey);
        String[] jsonChars = jsonChar.split(findKey, -1);
        String retVal = "";
        for (int i = 0; i < matchCount; i++ )
        {
            retVal += jsonChars[i] + (findKey + i);
        }
        retVal += jsonChars[matchCount];
        return retVal;
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
    private SchedulerEntity parseSchedule(JSONObject jsonObj, Date insertTime)
        throws Exception
    {
        if (null == jsonObj)
        {
            LOG.error("Parse obj is null.");
            return null;
        }
        
        SchedulerEntity schedulerEntity = new SchedulerEntity();
        schedulerEntity.setMaxapps(jsonObj.getIntValue("maxApps"));
        
        JSONObject minResources = JSON.parseObject(jsonObj.get("minResources").toString());
        schedulerEntity.setMinresMem(minResources.getIntValue("memory"));
        schedulerEntity.setMinresCpu(minResources.getIntValue("vCores"));
        
        JSONObject maxResources = JSON.parseObject(jsonObj.get("maxResources").toString());
        schedulerEntity.setMaxresMem(maxResources.getIntValue("memory"));
        schedulerEntity.setMaxresCpu(maxResources.getIntValue("vCores"));
        
        JSONObject usedResources = JSON.parseObject(jsonObj.get("usedResources").toString());
        schedulerEntity.setUsedresMem(usedResources.getIntValue("memory"));
        schedulerEntity.setUsedresCpu(usedResources.getIntValue("vCores"));
        
        JSONObject steadyFairResources = JSON.parseObject(jsonObj.get("steadyFairResources").toString());
        schedulerEntity.setSteadyfairresMem(steadyFairResources.getIntValue("memory"));
        schedulerEntity.setSteadyfairresCpu(steadyFairResources.getIntValue("vCores"));
        
        JSONObject fairResources = JSON.parseObject(jsonObj.get("fairResources").toString());
        schedulerEntity.setFairresMem(fairResources.getIntValue("memory"));
        schedulerEntity.setFairresCpu(fairResources.getIntValue("vCores"));
        
        JSONObject clusterResources = JSON.parseObject(jsonObj.get("clusterResources").toString());
        schedulerEntity.setClusterresMem(clusterResources.getIntValue("memory"));
        schedulerEntity.setClusterresCpu(clusterResources.getIntValue("vCores"));
        
        schedulerEntity.setPendingcountainers(jsonObj.getIntValue("pendingContainers"));
        schedulerEntity.setAllocatedcontainers(jsonObj.getIntValue("allocatedContainers"));
        schedulerEntity.setReservedcontainers(jsonObj.getIntValue("reservedContainers"));
        schedulerEntity.setQueuename(jsonObj.getString("queueName"));
        schedulerEntity.setSchedulingpolicy(jsonObj.getString("schedulingPolicy"));
        schedulerEntity.setPreemptable(jsonObj.getBooleanValue("preemptable"));
        schedulerEntity.setNumPendingApps(jsonObj.getIntValue("numPendingApps"));
        schedulerEntity.setNumActiveApps(jsonObj.getIntValue("numActiveApps"));
        schedulerEntity.setInserttime(insertTime);
        return schedulerEntity;
    }
    
    /**
     * Description: 组装内存，cpu监控结果，使用map进行组装，方便页面展现
     * 
     * @param monitor
     *            数据返回总集合
     * @param scheduler
     *            查询到的调度数据
     * @param monitorType
     *            监控类型，分为cpu和mem
     * @param queueEntities
     *            队列列表
     * @see
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void packageData(Map<String, Map<String, List[]>> monitor, SchedulerEntity scheduler,
                             String monitorType, List<String> leafQueue)
    {
        // 先判断该数据所对应的数据是否是叶子队列，如果不是，则不进行计算
        if (!leafQueue.contains(scheduler.getQueuename()))
        {
            return;
        }
        
        Map<String, List[]> subMap = monitor.get(monitorType);
        if (null == subMap)
        {
            subMap = new HashMap<String, List[]>();
            monitor.put(monitorType, subMap);
        }
        
        List[] val = subMap.get(scheduler.getQueuename());
        if (null == val)
        {
            val = new List[2];
            List<String> x = new ArrayList<String>();
            List<String> y = new ArrayList<String>();
            val[0] = x;
            val[1] = y;
            subMap.put(scheduler.getQueuename(), val);
        }
        
        if ("mem".equalsIgnoreCase(monitorType))
        {
            val[0].add("" + scheduler.getUsedresMem());
        }
        else
        {
            val[0].add("" + scheduler.getUsedresCpu());
        }
        val[1].add(formate.format(scheduler.getInserttime()));
    }
    
    /**
     * Description:获取调度任务的开始时间和结束时间
     * 
     * @param timeType
     *            时间类型
     * @return 返回计算之后的开始时间和结束时间
     */
    private Map<String, Object> getTime(String timeType)
    {
        TimeTypeEnum typeEnum = TimeTypeEnum.getTimeType(timeType);
        Calendar calendar = Calendar.getInstance();
        Map<String, Object> timeMap = new HashMap<String, Object>();
        timeMap.put("endTime", formate.format(calendar.getTime()));
        
        switch (typeEnum)
        {
            case TIME_30_Min:
                calendar.add(Calendar.MINUTE, -30);
                break;
            case TIME_1_Hour:
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                break;
            case TIME_6_Hour:
                calendar.add(Calendar.HOUR_OF_DAY, -6);
                break;
            case TIME_12_Hour:
                calendar.add(Calendar.HOUR_OF_DAY, -12);
                break;
            case TIME_1_Day:
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case TIME_1_Week:
                calendar.add(Calendar.WEEK_OF_MONTH, -1);
                break;
            case TIME_1_Month:
                calendar.add(Calendar.MONTH, -1);
                break;
            
            default:
                break;
        }
        timeMap.put("startTime", formate.format(calendar.getTime()));
        return timeMap;
    }
    
    @Override
    public Map<String, QueueResultEntity> queueMonitor(Map<String, Object> map)
    {
        String timeType = map.get("timeType").toString();
        
        Map<String, QueueResultEntity> monitor = new HashMap<String, QueueResultEntity>();
        Map<String, Object> params = getTime(timeType);
        params.put("queueName", map.get("queueName").toString());
        List<SchedulerEntity> schedulerList = schedulerDao.queueMonitor(params);
        
        for (SchedulerEntity scheduler : schedulerList)
        {
            // cpu 数据组装
            packageQueueData(monitor, scheduler, "cpu");
            
            // 内存组装
            packageQueueData(monitor, scheduler, "mem");
        }

        return monitor;
    }
    
    /**
     * Description: 组装内存，cpu监控结果，使用map进行组装，方便页面展现
     * 
     * @param monitor
     *            数据返回总集合
     * @param scheduler
     *            查询到的调度数据
     * @param monitorType
     *            监控类型，分为cpu和mem
     * @param queueEntities
     *            队列列表
     * @see
     */
    @SuppressWarnings({"unchecked"})
    private void packageQueueData(Map<String, QueueResultEntity> monitor,
                                  SchedulerEntity scheduler,
                                  String monitorType)
    {
        QueueResultEntity queueResultEntity = monitor.get(monitorType);
        if (null == queueResultEntity)
        {
            queueResultEntity = new QueueResultEntity();
            if ("mem".equalsIgnoreCase(monitorType))
            {
                queueResultEntity.setContainerSize(scheduler.getSteadyfairresMem());
            }
            else
            {
                queueResultEntity.setContainerSize(scheduler.getSteadyfairresCpu());
            }
            List<String>[] val = new List[2];
            List<String> x = new ArrayList<String>();
            List<String> y = new ArrayList<String>();
            val[0] = x;
            val[1] = y;
            queueResultEntity.setValues(val);
            monitor.put(monitorType, queueResultEntity);
        }
        
        if ("mem".equalsIgnoreCase(monitorType))
        {
            queueResultEntity.getValues()[0].add("" + scheduler.getUsedresMem());
            queueResultEntity.setContainerSize(scheduler.getSteadyfairresMem());
            
            if (queueResultEntity.getMaxRes() < scheduler.getUsedresMem())
            {
                queueResultEntity.setMaxRes(scheduler.getUsedresMem());
            }
        }
        else
        {
            queueResultEntity.getValues()[0].add("" + scheduler.getUsedresCpu());
            queueResultEntity.setContainerSize(scheduler.getSteadyfairresCpu());
            if (queueResultEntity.getMaxRes() < scheduler.getUsedresCpu())
            {
                queueResultEntity.setMaxRes(scheduler.getUsedresCpu());
            }
        }
        queueResultEntity.getValues()[1].add(formate.format(scheduler.getInserttime()));
    }

    /**
     * 删除当前时间之前1个月的数据
     */
    @Override
    public void deleteByInsertTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        schedulerDao.deleteByInsertTime(formate.format(calendar.getTime()));
        applicationDao.deleteByFinishTime(formate.format(calendar.getTime()));
    }
}

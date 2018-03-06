package com.bitauto.bdc.modules.yarnApplication.monitor.dao;

import com.bitauto.bdc.modules.sys.dao.BaseDao;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.StatisticsEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-24 12:11:41
 */
@Mapper
public interface YarnApplicationDao extends BaseDao<YarnApplicationEntity> {

    List<String> getQueneList();

    List<StatisticsEntity> getCountByQueneAndState();

    int getCountByQuene(String quene);

    /**
     * 获取每个用户的所有任务
     * @return
     */
    List<StatisticsEntity> getCountByUser(Map<String, Object> map);


    List<StatisticsEntity> getCountByUserAndState();

    /**
     *
     * @param state
     * @return
     */
    List<YarnApplicationEntity> getRunnigTask(String state);

    List<YarnApplicationEntity> queryListbyState(Map<String, Object> map);

    List<StatisticsEntity> getQueueTaskCountByState(String state);

    /**
     * Description: 查询任务列表通过时间和队列名称
     * 
     * @param map
     * @return 
     * @see
     */
    List<YarnApplicationEntity> queryListbyQueue(Map<String, Object> map);
    
    /**
     * Description: 查询任务数目通过时间和队列名称
     * 
     * @param map
     * @return 
     * @see
     */
    int queryTotalbyQueue(Map<String, Object> map);
    
    /**
     * Description: 通过任务完成时间，删除一个月之前的数据
     * 
     * @param finishedTime 
     * @see
     */
    void deleteByFinishTime(String oldTime);
}

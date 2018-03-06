package com.bitauto.bdc.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bitauto.bdc.modules.job.entity.ScheduleJobEntity;
import com.bitauto.bdc.modules.sys.dao.BaseDao;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}

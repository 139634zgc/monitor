package com.bitauto.bdc.modules.oozieServer.dao;

import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;
import com.bitauto.bdc.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WfJobsDao extends BaseDao<WfJobs> {

    public List<WfJobs> queryListcount(Map<String, Object> map);
    public List<WfJobs> queryfinishcount(Map<String, Object> map);
    public List<WfJobs> queryExecutionTimecount(Map<String, Object> map);
    public List<WfJobs> queryListByUsernamecount(Map<String, Object> map);
}

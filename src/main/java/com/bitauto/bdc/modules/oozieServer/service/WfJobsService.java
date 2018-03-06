package com.bitauto.bdc.modules.oozieServer.service;

import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;

import java.util.List;
import java.util.Map;

public interface WfJobsService {

    int queryTotal(Map<String, Object> map);
    List<WfJobs> queryList(Map<String, Object> map);
    List<WfJobs> queryListcount(Map<String, Object> map);
    public List<WfJobs> queryFinishcount(Map<String, Object> map);
    public List<WfJobs> queryExecutionTimecount(Map<String, Object> map);
    public List<WfJobs> queryListByUsernamecount(Map<String, Object> map);

}

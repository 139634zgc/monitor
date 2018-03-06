package com.bitauto.bdc.modules.oozieServer.service.impl;


import com.bitauto.bdc.common.annotation.DataSource;
import com.bitauto.bdc.dynamicdatasource.DataSourceNames;
import com.bitauto.bdc.modules.oozieServer.dao.WfJobsDao;
import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;
import com.bitauto.bdc.modules.oozieServer.service.WfJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("wfJobsServiceImpl")
public class WfJobsServiceImpl implements WfJobsService{

    @Autowired
    private  WfJobsDao WfJobsDao;

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public int queryTotal(Map<String, Object> map) {
        return WfJobsDao.queryTotal(map);
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public List<WfJobs> queryList(Map<String, Object> map) {
        List<WfJobs> list = WfJobsDao.queryList(map);
        return list;
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public List<WfJobs> queryListcount(Map<String, Object> map) {
        List<WfJobs> list = WfJobsDao.queryListcount(map);
        return list;
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public List<WfJobs> queryFinishcount(Map<String, Object> map) {
        List<WfJobs> list = WfJobsDao.queryfinishcount(map);
        return list;
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public List<WfJobs> queryExecutionTimecount(Map<String, Object> map) {
        List<WfJobs> list = WfJobsDao.queryExecutionTimecount(map);
        return list;
    }

    @Override
    @DataSource(name = DataSourceNames.OOZIE)
    public List<WfJobs> queryListByUsernamecount(Map<String, Object> map){
        List<WfJobs> list = WfJobsDao.queryListByUsernamecount(map);
        return list;
    }
}

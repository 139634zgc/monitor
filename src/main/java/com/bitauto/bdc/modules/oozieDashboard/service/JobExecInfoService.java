package com.bitauto.bdc.modules.oozieDashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitauto.bdc.modules.oozieDashboard.dao.JobExecInfoDao;
import com.bitauto.bdc.modules.oozieDashboard.model.JobExecInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.JobInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;
import com.bitauto.bdc.modules.oozieDashboard.util.HttpClientUtils;
import com.bitauto.bdc.modules.oozieDashboard.util.JobPraseUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by michealzhang on 2017/8/16.
 */
@Service
public class JobExecInfoService {

    @Autowired
    private JobExecInfoDao jobExecInfoDao;

    @Autowired
    private  JobInfoService jobInfoService;

    @Autowired
    private ProjectInfoService projectInfoService;

    public int addJobExecInfo(JobExecInfo jobExecInfo){
        jobExecInfo.setAddTime(new Date());
        return jobExecInfoDao.insertSelective(jobExecInfo);
    }

    public List<JobExecInfo> selectByJobId(String jobId){
        return jobExecInfoDao.selectByJobId(jobId);
    }

    public JobExecInfo getJobExceInfoByOozie(String jobId){
        String url = "http://172.20.0.67:11000/oozie/v1/job/";
        String res = null;
        try {
            res = HttpClientUtils.get(url+jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JobPraseUtil.parseJobExecJson(res);
    }

    /**
     * 更新任务的执行状态
     */
    public void updateJobExecInfo() {
        List<ProjectInfo> list = projectInfoService.getProjectList();
        for (ProjectInfo projectInfo : list) {
            List<JobInfo> jobInfoList = jobInfoService.getJobListByProjectId(projectInfo.getId());

            jobInfoList.forEach(jobInfo -> {
                JobExecInfo jobExecInfo = this.getJobExceInfoByOozie(jobInfo.getJobId());
                this.addJobExecInfo(jobExecInfo);
            });

        }

    }

}

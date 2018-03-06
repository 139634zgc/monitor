package com.bitauto.bdc.modules.oozieDashboard.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitauto.bdc.modules.oozieDashboard.dao.JobExecInfoDao;
import com.bitauto.bdc.modules.oozieDashboard.dao.JobInfoDao;
import com.bitauto.bdc.modules.oozieDashboard.model.JobExecInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.JobInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.vo.JobDetailVO;
import com.bitauto.bdc.modules.oozieDashboard.util.HttpClientUtils;
import com.bitauto.bdc.modules.oozieDashboard.util.JobPraseUtil;
import com.bitauto.bdc.modules.oozieDashboard.util.TimeUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by michealzhang on 2017/8/15.
 */
@Service
public class JobInfoService {

    @Autowired
    private JobInfoDao jobInfoMapper;

    @Autowired
    private JobExecInfoDao jobExecInfoDao;



    public List<JobInfo> getJobListByProjectId(int projectId){
        return jobInfoMapper.getJobListByProjectId(projectId);
    }

    public JobInfo getJobInfobyJobId(String jobId){
        return this.jobInfoMapper.selectByJobId(jobId);
    }

    /**
     * 根据jobId 查询oozie rest 接口 获得 任务详情
     * @param jobId
     * @return
     */
    private JobInfo  getJobInfobyOozie(String jobId){
        String url = "http://172.20.0.67:11000/oozie/v1/job/";
        String res = null;
        try {
            res = HttpClientUtils.get(url+jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JobPraseUtil.parseJobDetailJson(res);
    }

    public int saveJobInfo(String jobId,int projectId){
        JobInfo jobInfo = this.getJobInfobyOozie(jobId);
        return this.addJobToProject(jobInfo,projectId);

    }

    /**
     * 给某个项目添加job
     * @param jobInfo
     * @param projectId
     * @return
     */
    public int addJobToProject(JobInfo jobInfo,int projectId){
        jobInfo.setProjectId(projectId);
        JobInfo info = jobInfoMapper.getJobInfoByProjectIdAndJobId(projectId,jobInfo.getJobId());
        if(null == info){
            return jobInfoMapper.insertSelective(jobInfo);
        }
        return 0;
    }

    /**
     * 将某个job 从该项目下移除
     * @param projectId
     * @param jobId
     * @return
     */
    public int removeJob(int projectId,String jobId){
        return jobInfoMapper.removeJob(projectId,jobId);
    }


    public List<JobDetailVO> getJobDetail(Integer projectId){
        List<JobInfo> jobInfoList = this.getJobListByProjectId(projectId);
        List<JobDetailVO> list = new ArrayList<>();
        jobInfoList.forEach(jobInfo -> {
            JobDetailVO jobDetailVO = new JobDetailVO();

            try {
                BeanUtils.copyProperties(jobDetailVO,jobInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            List<JobExecInfo> jobExecInfoList = jobExecInfoDao.selectByJobId(jobInfo.getJobId());
            long execTimeTotal = 0;
            for (JobExecInfo jobExecInfo:jobExecInfoList) {
                if(TimeUtils.getBetweenDays(jobExecInfo.getAddTime(),new Date()) == 0){
                    jobDetailVO.setStartTime(jobExecInfo.getStartTime());
                    jobDetailVO.setEndTime(jobExecInfo.getEndTime());
                    jobDetailVO.setStatus(jobExecInfo.getStatus());
                    String exceTime = TimeUtils.showDistanceTimes(TimeUtils.getDistanceTimes(jobExecInfo.getEndTime(),jobExecInfo.getStartTime()));
                    jobDetailVO.setExecTime(exceTime);
                }
                execTimeTotal += Long.valueOf(jobExecInfo.getExecTime());
            }
            jobDetailVO.setAvgTime(String.valueOf(execTimeTotal/3));
            list.add(jobDetailVO);
        });

        return  list;
    }


}

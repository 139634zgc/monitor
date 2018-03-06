package com.bitauto.bdc.modules.oozieDashboard.model.vo;

import java.util.Date;

import com.bitauto.bdc.modules.oozieDashboard.util.TimeUtils;

/**
 * Created by michealzhang on 2017/8/22.
 */
public class JobDetailVO {

    private String jobId;

    private String jobName;

    private Integer projectId;

    private String authName;

    private Integer jobFlag;

    private Date startTime;

    private Date endTime;

    private String status;

    private String execTime;

    // 平均耗时
    private String avgTime;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Integer getJobFlag() {
        return jobFlag;
    }

    public void setJobFlag(Integer jobFlag) {
        this.jobFlag = jobFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }
}

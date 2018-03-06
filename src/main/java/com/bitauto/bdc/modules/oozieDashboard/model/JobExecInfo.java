package com.bitauto.bdc.modules.oozieDashboard.model;

import java.util.Date;

public class JobExecInfo {
    private Integer id;

    private String jobId;

    private String jobName;

    private Date startTime;

    private Date endTime;

    private String status;

    private String execTime;

    private Date addTime;

    public JobExecInfo(Integer id, String jobId, String jobName, Date startTime, Date endTime, String status, String execTime, Date addTime) {
        this.id = id;
        this.jobId = jobId;
        this.jobName = jobName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.execTime = execTime;
        this.addTime = addTime;
    }

    public JobExecInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime == null ? null : execTime.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
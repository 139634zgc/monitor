package com.bitauto.bdc.modules.oozieDashboard.model;

import java.util.Date;

public class JobInfo {
    private Integer id;

    private String jobId;

    private String jobName;

    private Integer projectId;

    private Integer tableId;

    private String authName;

    private Integer jobFlag;

    private Date updateTime;

    private Date createTime;

    public JobInfo(Integer id, String jobId, String jobName, Integer projectId, Integer tableId, String authName, Integer jobFlag, Date updateTime, Date createTime) {
        this.id = id;
        this.jobId = jobId;
        this.jobName = jobName;
        this.projectId = projectId;
        this.tableId = tableId;
        this.authName = authName;
        this.jobFlag = jobFlag;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public JobInfo() {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public Integer getJobFlag() {
        return jobFlag;
    }

    public void setJobFlag(Integer jobFlag) {
        this.jobFlag = jobFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
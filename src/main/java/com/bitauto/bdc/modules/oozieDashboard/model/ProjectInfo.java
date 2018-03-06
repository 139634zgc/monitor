package com.bitauto.bdc.modules.oozieDashboard.model;

import java.util.Date;

public class ProjectInfo {
    private Integer id;

    private String projectName;

    private String projectDesc;

    private String department;

    private String contacts;

    private String mailTo;

    private String mailCc;

    private Integer removeFlag;

    private Date createTime;

    private Date updateTime;

    private int jobCount;

    public ProjectInfo(Integer id, String projectName, String projectDesc, String department, String contacts, String mailTo, String mailCc, Integer removeFlag, Date createTime, Date updateTime) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.department = department;
        this.contacts = contacts;
        this.mailTo = mailTo;
        this.mailCc = mailCc;
        this.removeFlag = removeFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProjectInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo == null ? null : mailTo.trim();
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc == null ? null : mailCc.trim();
    }

    public Integer getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Integer removeFlag) {
        this.removeFlag = removeFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }
}
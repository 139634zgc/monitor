package com.bitauto.bdc.modules.yarnApplication.monitor.entity;

import java.io.Serializable;

/**
 * Created by michealzhang on 2017/11/3.
 */
public class TaskListEntity implements Serializable{

    private String date;
    private String userName;
    private String queneName;
    private int taskNum;
    private int failNum;
    private int successNum;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQueneName() {
        return queneName;
    }

    public void setQueneName(String queneName) {
        this.queneName = queneName;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

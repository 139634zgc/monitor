package com.bitauto.bdc.modules.oozieServer.entity;

import java.io.Serializable;

public class WfJobs  implements Serializable {

    private  String appName;
    private  String userName;
    private String startTime;
    private String endTime;
    private String status;
    private String xName ;
    private Integer sum;
    private Integer times;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}

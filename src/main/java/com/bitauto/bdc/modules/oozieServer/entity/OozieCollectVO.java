package com.bitauto.bdc.modules.oozieServer.entity;

import java.io.Serializable;

public class OozieCollectVO  implements Serializable {

    private  int allnum;
    private  int successnum ;
    private  int failnum ;


    private int jobcount;
    private  int errorcount;
    private  int succeededcount;
    private  int runingcount;
    private  int readycount;
    private  int waitingcount;

    private  String charkey ;
    private  String chardata ;

    private  String charFinishkey ;
    private  String charFinishdata ;

    private  String charExecutionTimekey ;
    private  String charExecutionTimedata ;

    private  String usernameStarTimetkey ;
    private  String usernameStarTimetdata ;
    private  String legendData;


    public int getAllnum() {
        return allnum;
    }

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    public int getSuccessnum() {
        return successnum;
    }

    public void setSuccessnum(int successnum) {
        this.successnum = successnum;
    }

    public int getFailnum() {
        return failnum;
    }

    public void setFailnum(int failnum) {
        this.failnum = failnum;
    }

    public String getCharkey() {
        return charkey;
    }

    public void setCharkey(String charkey) {
        this.charkey = charkey;
    }

    public String getChardata() {
        return chardata;
    }

    public void setChardata(String chardata) {
        this.chardata = chardata;
    }


    public int getJobcount() {
        return jobcount;
    }

    public void setJobcount(int jobcount) {
        this.jobcount = jobcount;
    }

    public int getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(int errorcount) {
        this.errorcount = errorcount;
    }

    public int getSucceededcount() {
        return succeededcount;
    }

    public void setSucceededcount(int succeededcount) {
        this.succeededcount = succeededcount;
    }

    public int getRuningcount() {
        return runingcount;
    }

    public void setRuningcount(int runingcount) {
        this.runingcount = runingcount;
    }

    public int getReadycount() {
        return readycount;
    }

    public void setReadycount(int readycount) {
        this.readycount = readycount;
    }

    public int getWaitingcount() {
        return waitingcount;
    }

    public void setWaitingcount(int waitingcount) {
        this.waitingcount = waitingcount;
    }

    public String getCharFinishkey() {
        return charFinishkey;
    }

    public void setCharFinishkey(String charFinishkey) {
        this.charFinishkey = charFinishkey;
    }

    public String getCharFinishdata() {
        return charFinishdata;
    }

    public void setCharFinishdata(String charFinishdata) {
        this.charFinishdata = charFinishdata;
    }

    public String getCharExecutionTimekey() {
        return charExecutionTimekey;
    }

    public void setCharExecutionTimekey(String charExecutionTimekey) {
        this.charExecutionTimekey = charExecutionTimekey;
    }

    public String getCharExecutionTimedata() {
        return charExecutionTimedata;
    }

    public void setCharExecutionTimedata(String charExecutionTimedata) {
        this.charExecutionTimedata = charExecutionTimedata;
    }


    public String getUsernameStarTimetkey() {
        return usernameStarTimetkey;
    }

    public void setUsernameStarTimetkey(String usernameStarTimetkey) {
        this.usernameStarTimetkey = usernameStarTimetkey;
    }

    public String getUsernameStarTimetdata() {
        return usernameStarTimetdata;
    }

    public void setUsernameStarTimetdata(String usernameStarTimetdata) {
        this.usernameStarTimetdata = usernameStarTimetdata;
    }

    public String getLegendData() {
        return legendData;
    }

    public void setLegendData(String legendData) {
        this.legendData = legendData;
    }
}

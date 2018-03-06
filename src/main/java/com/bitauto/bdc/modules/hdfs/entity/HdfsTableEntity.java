package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/11/30.
 */
public class HdfsTableEntity implements Serializable {
    private String tblName;
    private String tblOwner;
    private String dbLocation;
    private String dbName;
    private String dbOwner;
    private String tblLocation;
    private Long dbSize;
    private Long tblSize;
    private Long hdfsTablestatisId;
    private Integer newest;
    private Long increaseDisk = 0L;
    private String createDate;
    private Date createTime;


    public Integer getNewest() {
        return newest;
    }

    public void setNewest(Integer newest) {
        this.newest = newest;
    }

    public Long getIncreaseDisk() {
        return increaseDisk;
    }

    public void setIncreaseDisk(Long increaseDisk) {
        this.increaseDisk = increaseDisk;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getHdfsTablestatisId() {
        return hdfsTablestatisId;
    }

    public void setHdfsTablestatisId(Long hdfsTablestatisId) {
        this.hdfsTablestatisId = hdfsTablestatisId;
    }

    public Long getDbSize() {
        return dbSize;
    }

    public void setDbSize(Long dbSize) {
        this.dbSize = dbSize;
    }

    public Long getTblSize() {
        return tblSize;
    }

    public void setTblSize(Long tblSize) {
        this.tblSize = tblSize;
    }

    public String getTblLocation() {
        return tblLocation;
    }

    public void setTblLocation(String tblLocation) {
        this.tblLocation = tblLocation;
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getTblOwner() {
        return tblOwner;
    }

    public void setTblOwner(String tblOwner) {
        this.tblOwner = tblOwner;
    }

    public String getDbLocation() {
        return dbLocation;
    }

    public void setDbLocation(String dbLocation) {
        this.dbLocation = dbLocation;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbOwner() {
        return dbOwner;
    }

    public void setDbOwner(String dbOwner) {
        this.dbOwner = dbOwner;
    }

}

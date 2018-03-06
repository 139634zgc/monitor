package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/12/5.
 */
public class HdfsDbEntity implements Serializable {
    private Long hdfsDbstatisId;
    private String dbLocation;
    private String dbName;
    private String dbOwner;
    private Long dbSize;
    private Long increaseDisk = 0L;
    private String createDate;
    private Date createTime;

    public Long getIncreaseDisk() {
        return increaseDisk;
    }

    public void setIncreaseDisk(Long increaseDisk) {
        this.increaseDisk = increaseDisk;
    }

    public Long getHdfsDbstatisId() {
        return hdfsDbstatisId;
    }

    public void setHdfsDbstatisId(Long hdfsDbstatisId) {
        this.hdfsDbstatisId = hdfsDbstatisId;
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

    public Long getDbSize() {
        return dbSize;
    }

    public void setDbSize(Long dbSize) {
        this.dbSize = dbSize;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/11/28.
 */
public class HdfsSmallFileDailyStatisEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long hdfsSmallfileDailystatisId;
    private Long smallFileCount;
    private Long smallDirCount;
    private String createDate;
    private Date createTime;

    public Long getHdfsSmallfileDailystatisId() {
        return hdfsSmallfileDailystatisId;
    }

    public void setHdfsSmallfileDailystatisId(Long hdfsSmallfileDailystatisId) {
        this.hdfsSmallfileDailystatisId = hdfsSmallfileDailystatisId;
    }

    public Long getSmallFileCount() {
        return smallFileCount;
    }

    public void setSmallFileCount(Long smallFileCount) {
        this.smallFileCount = smallFileCount;
    }

    public Long getSmallDirCount() {
        return smallDirCount;
    }

    public void setSmallDirCount(Long smallDirCount) {
        this.smallDirCount = smallDirCount;
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

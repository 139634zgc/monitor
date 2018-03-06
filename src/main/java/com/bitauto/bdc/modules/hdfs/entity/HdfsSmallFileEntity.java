package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/11/16.
 */
public class HdfsSmallFileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long hdfsSmallfileId;
    private String directory;
    private Long fileCount;
    private Long totalSize;
    private Long avgSize;
    private String username;
    private Date createTime;

    public Long getHdfsSmallfileId() {
        return hdfsSmallfileId;
    }

    public void setHdfsSmallfileId(Long hdfsSmallfileId) {
        this.hdfsSmallfileId = hdfsSmallfileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAvgSize() {
        return avgSize;
    }

    public void setAvgSize(Long avgSize) {
        this.avgSize = avgSize;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
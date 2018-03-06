package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/10/23.
 */
public class HdfsMonitorItemEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long hdfsMonitorItemId;
    //总文件个数
    private Long fileCount;
    //总block数
    private Long blockCount;
    //总磁盘空间
    private Long totalDisk;
    //已使用磁盘空间
    private Long usedDisk;
    //日新增磁盘大小
    private Long increaseDisk;
    //namenode总内存
    private Long namenodeTotalMomory;
    //namenode已消耗内存
    private Long namenodeUsedMomory;
    //创建日期
    private String createDate;
    //创建时间
    private Date createTime;

    public Long getHdfsMonitorItemId() {
        return hdfsMonitorItemId;
    }

    public void setHdfsMonitorItemId(Long hdfsMonitorItemId) {
        this.hdfsMonitorItemId = hdfsMonitorItemId;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }

    public Long getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(Long blockCount) {
        this.blockCount = blockCount;
    }

    public Long getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(Long totalDisk) {
        this.totalDisk = totalDisk;
    }

    public Long getUsedDisk() {
        return usedDisk;
    }

    public void setUsedDisk(Long usedDisk) {
        this.usedDisk = usedDisk;
    }

    public Long getIncreaseDisk() {
        return increaseDisk;
    }

    public void setIncreaseDisk(Long increaseDisk) {
        this.increaseDisk = increaseDisk;
    }

    public Long getNamenodeUsedMomory() {
        return namenodeUsedMomory;
    }

    public void setNamenodeUsedMomory(Long namenodeUsedMomory) {
        this.namenodeUsedMomory = namenodeUsedMomory;
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

    public Long getNamenodeTotalMomory() {
        return namenodeTotalMomory;
    }

    public void setNamenodeTotalMomory(Long namenodeTotalMomory) {
        this.namenodeTotalMomory = namenodeTotalMomory;
    }
}

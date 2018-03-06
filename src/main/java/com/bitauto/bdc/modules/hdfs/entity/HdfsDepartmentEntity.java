package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/12/28.
 */
public class HdfsDepartmentEntity implements Serializable{
    private Long hdfsDepartmentId;
    private String name;
    private Long disk;
    private Date createTime;

    public Long getHdfsDepartmentId() {
        return hdfsDepartmentId;
    }

    public void setHdfsDepartmentId(Long hdfsDepartmentId) {
        this.hdfsDepartmentId = hdfsDepartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDisk() {
        return disk;
    }

    public void setDisk(Long disk) {
        this.disk = disk;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

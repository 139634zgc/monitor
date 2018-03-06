package com.bitauto.bdc.modules.hdfs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by weiyongxu on 2017/12/28.
 */
public class HdfsDepartmentDbEntity implements Serializable{
    private Long hdfsDepartmentDbId;
    private String dbName;
    private String owner;
    private Long departmentId;
    private String departmentName;
    private Date createTime;

    public Long getHdfsDepartmentDbId() {
        return hdfsDepartmentDbId;
    }

    public void setHdfsDepartmentDbId(Long hdfsDepartmentDbId) {
        this.hdfsDepartmentDbId = hdfsDepartmentDbId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

package com.bitauto.bdc.modules.yarnApplication.monitor.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by michealzhang on 2017/11/15.
 */
public class TaskDependentEntity implements Serializable {

    private String name;
    private String value;
    private List<TaskDependentEntity> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TaskDependentEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TaskDependentEntity> children) {
        this.children = children;
    }
}
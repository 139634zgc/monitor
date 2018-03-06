package com.bitauto.bdc.modules.yarnApplication.monitor.entity;

import java.io.Serializable;

/**
 * Created by michealzhang on 2017/11/7.
 * 统计 成功和失败的任务数，并跟进quene 和 状态分组
 */
public class StatisticsEntity implements Serializable {
    private int count;
    private String name;
    private String state;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

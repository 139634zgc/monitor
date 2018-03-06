package com.bitauto.bdc.modules.oozieServer.entity;

import java.util.List;
import java.util.Map;

public class SendMessageVo {

    private List<String> PrimaryTo;
    private Map<String,Object> data;
    private String groupId;
    private String weixinReceiver;

    public List<String> getPrimaryTo() {
        return PrimaryTo;
    }

    public void setPrimaryTo(List<String> primaryTo) {
        PrimaryTo = primaryTo;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getWeixinReceiver() {
        return weixinReceiver;
    }

    public void setWeixinReceiver(String weixinReceiver) {
        this.weixinReceiver = weixinReceiver;
    }
}

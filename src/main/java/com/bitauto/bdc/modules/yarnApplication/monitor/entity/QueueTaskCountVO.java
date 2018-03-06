package com.bitauto.bdc.modules.yarnApplication.monitor.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by michealzhang on 2017/11/14.
 */
public class QueueTaskCountVO implements Serializable {

//    var type =["阻塞","运行中"];
//    var data ={"pending":[1,2,3,4,5],"running":[1,2,3,4,5]};
//	var quene=["dw","psc","da","default","higth"];

    private List<String> queue;
    private Map<String,List<Integer>> data;

    public List<String> getQueue() {
        return queue;
    }

    public void setQueue(List<String> queue) {
        this.queue = queue;
    }

    public Map<String, List<Integer>> getData() {
        return data;
    }

    public void setData(Map<String, List<Integer>> data) {
        this.data = data;
    }
}

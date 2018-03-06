package com.bitauto.bdc.modules.job.task;

import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsMonitorItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by weiyongxu on 2017/10/26.
 */
@Component("hdfsMonitorItemTask")
public class HdfsMonitorItemTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HdfsMonitorItemService hdfsMonitorItemService;

    public void add() {
        HdfsMonitorItemEntity hdfsMonitorItemEntity = hdfsMonitorItemService.add();
        if(null == hdfsMonitorItemEntity) {
            logger.error("add hdfs monitor item task fail");
        }
    }
}

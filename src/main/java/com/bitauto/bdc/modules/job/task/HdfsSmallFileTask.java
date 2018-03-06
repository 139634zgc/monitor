package com.bitauto.bdc.modules.job.task;

import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by weiyongxu on 2017/11/21.
 */
@Component("hdfsSmallFileTask")
public class HdfsSmallFileTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HdfsSmallFileService hdfsSmallFileService;

    public void addBatch() {
        logger.info("small file add batch start");
        hdfsSmallFileService.addBatch();
    }
}

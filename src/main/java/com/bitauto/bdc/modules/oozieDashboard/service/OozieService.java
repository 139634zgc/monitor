package com.bitauto.bdc.modules.oozieDashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Created by michealzhang on 2017/8/16.
 */
@Component("oozieService")
public class OozieService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private JobExecInfoService jobExecInfoService;

    public void updateJobExceInfoService(){

        logger.info("updateJobExecInfo");
        jobExecInfoService.updateJobExecInfo();
    }

}

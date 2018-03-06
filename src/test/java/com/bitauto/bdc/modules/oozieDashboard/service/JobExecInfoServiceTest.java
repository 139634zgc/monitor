package com.bitauto.bdc.modules.oozieDashboard.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.modules.oozieDashboard.service.JobExecInfoService;

import static org.junit.Assert.*;

/**
 * Created by michealzhang on 2017/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
@Ignore
public class JobExecInfoServiceTest {

    @Autowired
    private JobExecInfoService jobExecInfoService;

    @Test
    public void updateJobExecInfo() throws Exception {
        jobExecInfoService.updateJobExecInfo();
    }

}
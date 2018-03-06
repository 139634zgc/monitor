package com.bitauto.bdc.modules.oozie.monitor.service.impl;

import com.bitauto.bdc.RenrenApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by michealzhang on 2017/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class WorkflowsServiceImplTest {

    @Test
    public void getWfByDoneFlag() throws Exception {
    }

}
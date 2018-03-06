package com.bitauto.bdc.modules.yarnApplication.monitor.schedule;

import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.common.utils.PropertyUtil;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import org.apache.shiro.util.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by michealzhang on 2017/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class YarnApplicationSyncServiceTest {


    @Value("${config.schedule-flag}")
    private  String PATH="";

    @Autowired
    private PropertyUtil propertyUtil;

    @Autowired
    private  YarnApplicationSyncService yarnApplicationSyncService;

    @Test
    public  void buildYarnApplicationEntityTest(){
        List<YarnApplicationEntity> list = yarnApplicationSyncService.buildYarnApplicationEntityList("");
        Assert.notNull(list);
        Assert.isTrue(list.size()==5);
    }

    @Test
    public void getYarnApplication(){
        yarnApplicationSyncService.getYarnApplication();
    }

    @Test
    public void updateYarnApplication(){
        yarnApplicationSyncService.updateYarnApplication();
    }

    @Test
    public void resourceTest(){
        System.out.println("-----");
        System.out.println(PATH);
        propertyUtil.setProperty("coordinator_offset","100");
    }


}
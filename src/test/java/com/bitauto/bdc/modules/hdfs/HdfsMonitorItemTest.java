package com.bitauto.bdc.modules.hdfs;

import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsMonitorItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/10/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class HdfsMonitorItemTest {
    @Autowired
    private HdfsMonitorItemService hdfsMonitorItemService;

    @Test
    public void list() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit", 10);
        paramMap.put("page", 1);
        List<HdfsMonitorItemEntity> hdfsMonitorItemList = hdfsMonitorItemService.queryList(paramMap);
        System.out.println(hdfsMonitorItemList);
    }

    @Test
    public void add() {
        HdfsMonitorItemEntity addEntiry = hdfsMonitorItemService.add();
        Assert.assertNotNull(addEntiry);
    }

    @Test
    public void addDiskDailyIncrease() {
        Integer r = hdfsMonitorItemService.addDiskDailyIncrease(new Date());
        Assert.assertNotNull(r);
    }

}

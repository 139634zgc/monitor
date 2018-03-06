package com.bitauto.bdc.modules.hdfs;

import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentDbService;
import com.bitauto.bdc.modules.hdfs.service.HdfsTableStatisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * Created by weiyongxu on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class HdfsTableTest {
    @Autowired
    private HdfsTableStatisService hdfsTableStatis;

    @Autowired
    private HdfsDepartmentDbService hdfsDepartmentDbService;

    @Test
    public void addBatch() {
        List<HdfsTableEntity> list = hdfsTableStatis.processListByFsimage();
        hdfsTableStatis.addBatch(list);
    }

    @Test
    public void addDbStatis() {
        List<HdfsTableEntity> list = hdfsTableStatis.getMetaDbList();
        hdfsTableStatis.addDbStatis(list);
    }

    @Test
    public void addDepartDiskStatis() {
        hdfsDepartmentDbService.addDepartDiskStatis();
    }
}

package com.bitauto.bdc.modules.hdfs;

import com.bitauto.bdc.RenrenApplication;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileService;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
@ActiveProfiles("test")
public class HdfsSmallFileTest {
    @Autowired
    private HdfsSmallFileService hdfsSmallFileService;

    @Test
    public void handleFile() {
        HashMap<String, HdfsSmallFileEntity> stringHdfsSmallFileEntityHashMap = hdfsSmallFileService.handleFsimageFile();
        Assert.notNull(stringHdfsSmallFileEntityHashMap);
        /*Iterator iterator = stringHdfsSmallFileEntityHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            HdfsSmallFileEntity hdfsSmallFileEntity = (HdfsSmallFileEntity)entry.getValue();
            System.out.println("directory:" + entry.getKey() + " ,count:" + hdfsSmallFileEntity.getFileCount() +
                    " ,totalsize:" + hdfsSmallFileEntity.getTotalSize() +
                    " ,username:" + hdfsSmallFileEntity.getUsername() +
                    " ,dir:" + hdfsSmallFileEntity.getDirectory() +
                    " ,avgSize:" + hdfsSmallFileEntity.getAvgSize());

        }*/
    }


    @Test
    public void add() {
        hdfsSmallFileService.addBatch();
    }
}

package com.bitauto.bdc.modules.job.task;

import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentDbService;
import com.bitauto.bdc.modules.hdfs.service.HdfsTableStatisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weiyongxu on 2017/12/1.
 */
@Component("hdfsTableStatisTask")
public class HdfsTableStatisTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HdfsTableStatisService hdfsTableStatisService;

    @Autowired
    private HdfsDepartmentDbService hdfsDepartmentDbService;

    public void addDbAndTableStatis() {
        List<HdfsTableEntity> list = hdfsTableStatisService.processListByFsimage();
        if(list != null) {
            hdfsTableStatisService.addBatch(list);
        } else {
            logger.error("add batch table statis fail");
        }

        List<HdfsTableEntity> list2 = hdfsTableStatisService.getMetaDbList();
        hdfsTableStatisService.addDbStatis(list2);
    }

    public void addDepartmentDiskStatis() {
        hdfsDepartmentDbService.addDepartDiskStatis();
    }

}
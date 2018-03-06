package com.bitauto.bdc.modules.hdfs.controller;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileDailyStatisEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsSmallFileEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileDailyStatisService;
import com.bitauto.bdc.modules.hdfs.service.HdfsSmallFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/17.
 */
@RestController
@RequestMapping("/hdfs/small_file")
public class HdfsSmallFileController {
    @Autowired
    private HdfsSmallFileService hdfsSmallFileService;

    @Autowired
    private HdfsSmallFileDailyStatisService hdfsSmallFileDailyStatisService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<HdfsSmallFileEntity> hdfsSmallFileList = hdfsSmallFileService.queryList(query);
        for(int i = 0; i < hdfsSmallFileList.size(); i++) {
            HdfsSmallFileEntity hdfsSmallFileEntity = hdfsSmallFileList.get(i);
            hdfsSmallFileEntity.setAvgSize(hdfsSmallFileEntity.getAvgSize()/(1024*1024));
            hdfsSmallFileEntity.setTotalSize(hdfsSmallFileEntity.getTotalSize()/(1024*1024));
            hdfsSmallFileList.set(i, hdfsSmallFileEntity);
        }
        int total = hdfsSmallFileService.queryTotal(params);
        PageUtils pageUtils = new PageUtils(hdfsSmallFileList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/list_daily_statis")
    public R listDailyStatis(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<HdfsSmallFileDailyStatisEntity> list = hdfsSmallFileDailyStatisService.queryList(query);
        int total = hdfsSmallFileDailyStatisService.queryTotal(params);
        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtils);
    }
}

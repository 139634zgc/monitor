package com.bitauto.bdc.modules.hdfs.controller;

import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.api.annotation.AuthIgnore;
import com.bitauto.bdc.modules.hdfs.constant.ErrorCode;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDbEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.hdfs.entity.HdfsTableEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDbStatisService;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentService;
import com.bitauto.bdc.modules.hdfs.service.HdfsMonitorItemService;
import com.bitauto.bdc.modules.hdfs.service.HdfsTableStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/10/23.
 */
@RestController
@RequestMapping("/hdfs")
public class HDFSController {
    @Autowired
    private HdfsMonitorItemService hdfsMonitorItemService;

    @Autowired
    private HdfsTableStatisService hdfsTableStatisService;

    @Autowired
    private HdfsDbStatisService hdfsDbStatisService;

    @Autowired
    private HdfsDepartmentService hdfsDepartmentService;

    @RequestMapping("/monitor_item/list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<HdfsMonitorItemEntity> hdfsMonitorItemList = hdfsMonitorItemService.queryList(query);
        int total = hdfsMonitorItemService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(hdfsMonitorItemList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/monitor_item/add")
    @AuthIgnore
    public R add() {
        HdfsMonitorItemEntity hdfsMonitorItemEntity = hdfsMonitorItemService.add();
        if(null == hdfsMonitorItemEntity) {
            return R.error(ErrorCode.ERR_HDFS_JMX_API.getCode(), ErrorCode.ERR_HDFS_JMX_API.getMsg());
        }

        return R.ok().put("monitor_item", hdfsMonitorItemEntity);
    }

    @RequestMapping("/table/list")
    public R listTable(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<HdfsTableEntity> list = hdfsTableStatisService.queryList(query);
        int total = hdfsTableStatisService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/db/list")
    public R listDb(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<HdfsDbEntity> list = hdfsDbStatisService.queryList(query);
        int total = hdfsDbStatisService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/db/list_increase_top")
    public R listDbIncreaseTop(@RequestParam Map<String, Object> params) {
        Date today = new Date();
        if(params.get("daterange").equals("1week")) {
            params.put("startTime", DateUtils.getAroundDate(today, -7, DateUtils.DATE_TIME_PATTERN));
        } else if (params.get("daterange").equals("2week")) {
            params.put("startTime", DateUtils.getAroundDate(today, -14, DateUtils.DATE_TIME_PATTERN));
        } if (params.get("daterange").equals("1month")) {
            params.put("startTime", DateUtils.getAroundDate(today, -30, DateUtils.DATE_TIME_PATTERN));
        }

        List<HdfsDbEntity> list = hdfsDbStatisService.listIncreaseDiskTop(params);
        PageUtils pageUtils = new PageUtils(list, list.size(), list.size(), 1);
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/table/list_increase_top")
    public R listTableIncreaseTop(@RequestParam Map<String, Object> params) {
        Date today = new Date();
        if(params.get("daterange").equals("1week")) {
            params.put("startTime", DateUtils.getAroundDate(today, -7, DateUtils.DATE_TIME_PATTERN));
        } else if (params.get("daterange").equals("2week")) {
            params.put("startTime", DateUtils.getAroundDate(today, -14, DateUtils.DATE_TIME_PATTERN));
        } if (params.get("daterange").equals("1month")) {
            params.put("startTime", DateUtils.getAroundDate(today, -30, DateUtils.DATE_TIME_PATTERN));
        }

        List<HdfsTableEntity> list = hdfsTableStatisService.listIncreaseDiskTop(params);
        PageUtils pageUtils = new PageUtils(list, list.size(), list.size(), 1);
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/db/list_top")
    public R listDbTop(@RequestParam Map<String, Object> params) {
        List<HdfsDbEntity> list = hdfsDbStatisService.listDiskTop(params);
        PageUtils pageUtils = new PageUtils(list, list.size(), list.size(), 1);
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/table/list_top")
    public R listTableTop(@RequestParam Map<String, Object> params) {
        List<HdfsTableEntity> list = hdfsTableStatisService.listDiskTop(params);
        PageUtils pageUtils = new PageUtils(list, list.size(), list.size(), 1);
        return R.ok().put("page", pageUtils);
    }

    @RequestMapping("/department/list")
    public R listDepartment(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<HdfsDepartmentEntity> hdfsDepartmentEntities = hdfsDepartmentService.queryList(query);
        int total = hdfsDepartmentService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(hdfsDepartmentEntities, total, total, 1);
        return R.ok().put("page", pageUtils);
    }
}

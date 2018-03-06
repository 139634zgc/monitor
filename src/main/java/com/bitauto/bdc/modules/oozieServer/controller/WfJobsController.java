package com.bitauto.bdc.modules.oozieServer.controller;


import com.bitauto.bdc.common.utils.DateUtils;
import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.hdfs.entity.HdfsMonitorItemEntity;
import com.bitauto.bdc.modules.oozieServer.entity.OozieCollectVO;
import com.bitauto.bdc.modules.oozieServer.entity.WfJobs;
import com.bitauto.bdc.modules.oozieServer.service.CoordJobsService;
import com.bitauto.bdc.modules.oozieServer.service.WfJobsService;
import com.bitauto.bdc.modules.oozieServer.util.UserGroupUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/oozieServer/wfjobsCollect")
public class WfJobsController {

    @Autowired
    private CoordJobsService coordJobsService;

    @Autowired
    private WfJobsService wfJobsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yarnapplication:list")
    public R list(@RequestParam Map<String, Object> params){
        List<String> list = new ArrayList<String>();
        String status = String.valueOf(params.get("status"));
        String times = String.valueOf(params.get("times"));

        if( params.get("startTime")!=null && !StringUtils.isEmpty(params.get("startTime").toString()) && params.get("endTime")!=null && !StringUtils.isEmpty(params.get("endTime").toString()) ){
            params.put("startTime",DateUtils.parseTime(params.get("startTime").toString()));
            params.put("endTime",DateUtils.parseTime(params.get("endTime").toString()));
        }else{
            params.put("startTime",null);
            params.put("endTime",null);
        }

        String username = String.valueOf(params.get("username"));
        if("数仓组".equals(username)){
            params.put("username", UserGroupUtil.getGroupUserName());
        }else  if(!StringUtils.isEmpty(username) && !"null".equals(username)){
            //map.put("username",username);
            list.add(username);
            params.put("usernameList",list);
        }

        if("succeeded".equals(status)){
            params.put("succeeded",status);
        }else if("running".equals(status)){
            params.put("running",status);
        }else if("failed".equals(status)){
            params.put("failed",status);
        }else if("waiting".equals(status)){
            params.put("waiting",status);
        }

       if( !StringUtils.isEmpty(times) && !"null".equals(times)){
           params.put("time_start",Integer.parseInt(times));
           params.put("time_end",Integer.parseInt(times)+4);
        }

        Query query = new Query(params);

        List<WfJobs> wfjobsList = wfJobsService.queryList(query);

        int total = wfJobsService.queryTotal(query);
        PageUtils pageUtils = new PageUtils(wfjobsList, total, query.getLimit(), query.getPage());


        return R.ok().put("page", pageUtils);
    }
}

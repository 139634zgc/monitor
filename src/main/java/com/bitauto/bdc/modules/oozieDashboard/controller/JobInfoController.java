package com.bitauto.bdc.modules.oozieDashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.oozieDashboard.model.JobInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;
import com.bitauto.bdc.modules.oozieDashboard.model.vo.JobDetailVO;
import com.bitauto.bdc.modules.oozieDashboard.service.JobInfoService;
import com.bitauto.bdc.modules.oozieDashboard.service.ProjectInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michealzhang on 2017/8/22.
 */
@RestController
@RequestMapping("get/job")
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping("/info")
//    @RequiresPermissions("oozie:project:select")
    public R info(@RequestParam Integer projectId,@RequestParam Map<String, Object> params){

        Query query = new Query(params);

        List<JobDetailVO> list = jobInfoService.getJobDetail(projectId);
        PageUtils pageUtil = new PageUtils(list, list.size(), query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    @RequestMapping("/{jobId}")
//    @RequiresPermissions("oozie:project:select")
    public R jobInfo(@PathVariable("jobId") String jobId){

        JobInfo info  = jobInfoService.getJobInfobyJobId(jobId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfo(info.getProjectId());

       Map<String,Object> res = new HashMap<>();
       res.put("projectId",info.getProjectId());
       res.put("jobId","");
       res.put("projectName",projectInfo.getProjectName());

       return R.ok().put("project", res);
    }

    @RequestMapping("/save")
    public R saveJobInfo(@RequestBody Map<String,Object> map){
        String jobId = String.valueOf(map.get("jobId"));
        int projectId = (int)map.get("projectId");
        jobInfoService.saveJobInfo(jobId,projectId);
        return R.ok();
    }

}

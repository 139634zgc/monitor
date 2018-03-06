package com.bitauto.bdc.modules.oozieDashboard.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.bitauto.bdc.common.annotation.SysLog;
import com.bitauto.bdc.common.utils.Constant;
import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.common.validator.ValidatorUtils;
import com.bitauto.bdc.modules.oozieDashboard.model.ProjectInfo;
import com.bitauto.bdc.modules.oozieDashboard.service.ProjectInfoService;
import com.bitauto.bdc.modules.sys.entity.SysRoleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michealzhang on 2017/8/18.
 */
@RestController
@RequestMapping("get/project")
public class ProjectController {

    @Autowired
    private ProjectInfoService projectInfoService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("oozie:project:list")
    public R list(@RequestParam Map<String, Object> params){

        //查询列表数据
        Query query = new Query(params);
        List<ProjectInfo> list = projectInfoService.getProjectByName(params);
        int total = projectInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("oozie:project:save")
    public R save(@RequestBody ProjectInfo projectInfo){
        ValidatorUtils.validateEntity(projectInfo);
        projectInfoService.insertSelective(projectInfo);
        return R.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("oozie:project:update")
    public R update(@RequestBody ProjectInfo projectInfo){
        ValidatorUtils.validateEntity(projectInfo);
        projectInfoService.updateProject(projectInfo.getId(),projectInfo);
        return R.ok();
    }

    /**
     * 项目信息
     */
    @RequestMapping("/{projectId}")
//    @RequiresPermissions("oozie:project:select")
    public R info(@PathVariable("projectId") Integer projectId){
        ProjectInfo projectInfo = projectInfoService.getProjectInfo(projectId);
        return R.ok().put("project", projectInfo);
    }


    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @PostMapping(value = "/delete")
    @RequiresPermissions("oozie:project:delete")
    public R delete(@RequestBody Integer[] roleIds){
        projectInfoService.deleteBatch(roleIds);
        return R.ok();
    }
}

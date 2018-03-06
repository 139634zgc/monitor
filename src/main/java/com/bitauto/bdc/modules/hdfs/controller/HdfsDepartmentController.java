package com.bitauto.bdc.modules.hdfs.controller;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyongxu on 2018/1/3.
 */
@RestController
@RequestMapping("/hdfs/hdfsdepartment")
public class HdfsDepartmentController {
    @Autowired
    private HdfsDepartmentService hdfsDepartmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("hdfs:hdfsdepartment:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<HdfsDepartmentEntity> hdfsDepartmentList = hdfsDepartmentService.queryList(query);
        int total = hdfsDepartmentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(hdfsDepartmentList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hdfsDepartmentId}")
    @RequiresPermissions("hdfs:hdfsdepartment:info")
    public R info(@PathVariable("hdfsDepartmentId") Long hdfsDepartmentId){
        HdfsDepartmentEntity hdfsDepartment = hdfsDepartmentService.queryObject(hdfsDepartmentId);

        return R.ok().put("hdfsDepartment", hdfsDepartment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("hdfs:hdfsdepartment:save")
    public R save(@RequestBody HdfsDepartmentEntity hdfsDepartment){
        hdfsDepartmentService.save(hdfsDepartment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("hdfs:hdfsdepartment:update")
    public R update(@RequestBody HdfsDepartmentEntity hdfsDepartment){
        hdfsDepartmentService.update(hdfsDepartment);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("hdfs:hdfsdepartment:delete")
    public R delete(@RequestBody Long[] hdfsDepartmentIds){
        hdfsDepartmentService.deleteBatch(hdfsDepartmentIds);

        return R.ok();
    }
}

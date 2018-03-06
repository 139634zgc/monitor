package com.bitauto.bdc.modules.hdfs.controller;

import java.util.List;
import java.util.Map;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.hdfs.entity.HdfsDepartmentDbEntity;
import com.bitauto.bdc.modules.hdfs.service.HdfsDepartmentDbService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weiyongxu on 2018/1/3.
 */
@RestController
@RequestMapping("/hdfs/hdfsdepartmentdb")
public class HdfsDepartmentDbController {
    @Autowired
    private HdfsDepartmentDbService hdfsDepartmentDbService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("hdfs:hdfsdepartmentdb:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<HdfsDepartmentDbEntity> hdfsDepartmentDbList = hdfsDepartmentDbService.queryList(query);
        int total = hdfsDepartmentDbService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(hdfsDepartmentDbList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hdfsDepartmentDbId}")
    @RequiresPermissions("hdfs:hdfsdepartmentdb:info")
    public R info(@PathVariable("hdfsDepartmentDbId") Long hdfsDepartmentDbId){
        HdfsDepartmentDbEntity hdfsDepartmentDb = hdfsDepartmentDbService.queryObject(hdfsDepartmentDbId);

        return R.ok().put("hdfsDepartmentDb", hdfsDepartmentDb);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("hdfs:hdfsdepartmentdb:save")
    public R save(@RequestBody HdfsDepartmentDbEntity hdfsDepartmentDb){
        hdfsDepartmentDbService.save(hdfsDepartmentDb);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("hdfs:hdfsdepartmentdb:update")
    public R update(@RequestBody HdfsDepartmentDbEntity hdfsDepartmentDb){
        hdfsDepartmentDbService.update(hdfsDepartmentDb);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("hdfs:hdfsdepartmentdb:delete")
    public R delete(@RequestBody Long[] hdfsDepartmentDbIds){
        hdfsDepartmentDbService.deleteBatch(hdfsDepartmentDbIds);

        return R.ok();
    }

}


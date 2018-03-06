package com.bitauto.bdc.modules.oozie.monitor.controller;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.oozie.monitor.entity.WorkflowsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.WorkflowsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;






/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
@RestController
@RequestMapping("/generator/workflows")
public class WorkflowsController {
	@Autowired
	private WorkflowsService workflowsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:workflows:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WorkflowsEntity> workflowsList = workflowsService.queryList(query);
		int total = workflowsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(workflowsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:workflows:info")
	public R info(@PathVariable("id") Long id){
		WorkflowsEntity workflows = workflowsService.queryObject(id);
		
		return R.ok().put("workflows", workflows);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:workflows:save")
	public R save(@RequestBody WorkflowsEntity workflows){
		workflowsService.save(workflows);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:workflows:update")
	public R update(@RequestBody WorkflowsEntity workflows){
		workflowsService.update(workflows);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:workflows:delete")
	public R delete(@RequestBody Long[] ids){
		workflowsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

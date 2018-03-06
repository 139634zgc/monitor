package com.bitauto.bdc.modules.oozie.monitor.controller;

import java.util.List;
import java.util.Map;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.oozie.monitor.entity.CoordinatorjobsEntity;
import com.bitauto.bdc.modules.oozie.monitor.service.CoordinatorjobsService;
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
@RequestMapping("/generator/coordinatorjobs")
public class CoordinatorjobsController {
	@Autowired
	private CoordinatorjobsService coordinatorjobsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:coordinatorjobs:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CoordinatorjobsEntity> coordinatorjobsList = coordinatorjobsService.queryList(query);
		int total = coordinatorjobsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(coordinatorjobsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:coordinatorjobs:info")
	public R info(@PathVariable("id") Long id){
		CoordinatorjobsEntity coordinatorjobs = coordinatorjobsService.queryObject(id);
		
		return R.ok().put("coordinatorjobs", coordinatorjobs);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:coordinatorjobs:save")
	public R save(@RequestBody CoordinatorjobsEntity coordinatorjobs){
		coordinatorjobsService.save(coordinatorjobs);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:coordinatorjobs:update")
	public R update(@RequestBody CoordinatorjobsEntity coordinatorjobs){
		coordinatorjobsService.update(coordinatorjobs);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:coordinatorjobs:delete")
	public R delete(@RequestBody Long[] ids){
		coordinatorjobsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

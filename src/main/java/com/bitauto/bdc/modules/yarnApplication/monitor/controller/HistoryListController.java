package com.bitauto.bdc.modules.yarnApplication.monitor.controller;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.TaskListEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-24 12:11:41
 */
@RestController
@RequestMapping("/generator/historylist")
public class HistoryListController {
	@Autowired
	private YarnApplicationService yarnApplicationService;
	
	/**
	 * 列表
	 */

	@RequestMapping("/quene")
	@RequiresPermissions("generator:historylist:quene")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TaskListEntity> queneListEntityList = yarnApplicationService.getQueneList(params);
		int total = queneListEntityList.size();
		
		PageUtils pageUtil = new PageUtils(queneListEntityList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/user")
	@RequiresPermissions("generator:historylist:user")
	public R userList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<TaskListEntity> queneListEntityList = yarnApplicationService.getUserTaskList(query);
		int total = yarnApplicationService.getUserTaskList(null).size();

		PageUtils pageUtil = new PageUtils(queneListEntityList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}



	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("generator:yarnapplication:info")
	public R info(@PathVariable("id") String id){
		YarnApplicationEntity yarnApplication = yarnApplicationService.queryObject(id);
		
		return R.ok().put("yarnApplication", yarnApplication);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:yarnapplication:save")
	public R save(@RequestBody YarnApplicationEntity yarnApplication){
		yarnApplicationService.save(yarnApplication);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:yarnapplication:update")
	public R update(@RequestBody YarnApplicationEntity yarnApplication){
		yarnApplicationService.update(yarnApplication);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("generator:yarnapplication:delete")
	public R delete(@RequestBody String[] ids){
		yarnApplicationService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

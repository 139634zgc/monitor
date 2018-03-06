package com.bitauto.bdc.modules.yarnApplication.monitor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bitauto.bdc.common.utils.PageUtils;
import com.bitauto.bdc.common.utils.Query;
import com.bitauto.bdc.common.utils.R;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.QueueTaskCountVO;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.TaskDependentEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.entity.YarnApplicationEntity;
import com.bitauto.bdc.modules.yarnApplication.monitor.service.YarnApplicationService;
import org.apache.commons.lang.StringUtils;
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
 * @date 2017-10-24 12:11:41
 */
@RestController
@RequestMapping("/generator/yarnapplication")
public class YarnApplicationController {


	@Autowired
	private YarnApplicationService yarnApplicationService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:yarnapplication:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YarnApplicationEntity> yarnApplicationList = yarnApplicationService.queryList(query);
		int total = yarnApplicationService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(yarnApplicationList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}


	@RequestMapping("/execlist")
	@RequiresPermissions("generator:yarnapplication:execlist")
	public R execList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<YarnApplicationEntity> yarnApplicationList = yarnApplicationService.queryListbyState(params);
		int total = yarnApplicationService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(yarnApplicationList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	@RequestMapping("/quene")
	@RequiresPermissions("generator:yarnapplication:quene")
	public R queneRunning(@RequestParam Map<String, Object> params){
		//查询列表数据
		QueueTaskCountVO queueTaskCountVO = yarnApplicationService.getQueneRunning();
		return R.ok().put("page", queueTaskCountVO);
	}

	@RequestMapping("/dependent")
	@RequiresPermissions("generator:yarnapplication:dependent")
	public R taksDependent(@RequestParam Map<String, Object> params){
		//查询列表数据
		String wfId = String.valueOf(params.get("wfId"));
		List<TaskDependentEntity> list = new ArrayList<>();
		if(StringUtils.isBlank(wfId) || wfId.equals("null")){
			list = yarnApplicationService.getTaskDependentTree();
		}else {
			list = yarnApplicationService.getTaskDependentTree(wfId);

		}
		return R.ok().put("page", list);
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

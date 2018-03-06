package com.bitauto.bdc.modules.oozie.monitor.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-02 16:25:54
 */
public class CoordinatorjobsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String coordjobid;
	//
	private String user;
	//
	private String coordjobname;
	//
	private String frequency;
	//
	private String timezone;
	//
	private Integer matThrottling;
	//
	private Integer total;
	//
	private String starttime;
	//
	private String endtime;
	//
	private String status;
	//
	private String executionpolicy;
	//
	private String actions;
	//
	private String group;
	//
	private String coordexternalid;
	//
	private String lastaction;
	//
	private String pausetime;
	//
	private Integer concurrency;
	//
	private Long timeout;
	//
	private String wfId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCoordjobid(String coordjobid) {
		this.coordjobid = coordjobid;
	}
	/**
	 * 获取：
	 */
	public String getCoordjobid() {
		return coordjobid;
	}
	/**
	 * 设置：
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * 获取：
	 */
	public String getUser() {
		return user;
	}
	/**
	 * 设置：
	 */
	public void setCoordjobname(String coordjobname) {
		this.coordjobname = coordjobname;
	}
	/**
	 * 获取：
	 */
	public String getCoordjobname() {
		return coordjobname;
	}
	/**
	 * 设置：
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * 获取：
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * 设置：
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	/**
	 * 获取：
	 */
	public String getTimezone() {
		return timezone;
	}
	/**
	 * 设置：
	 */
	public void setMatThrottling(Integer matThrottling) {
		this.matThrottling = matThrottling;
	}
	/**
	 * 获取：
	 */
	public Integer getMatThrottling() {
		return matThrottling;
	}
	/**
	 * 设置：
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取：
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置：
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * 设置：
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：
	 */
	public String getEndtime() {
		return endtime;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setExecutionpolicy(String executionpolicy) {
		this.executionpolicy = executionpolicy;
	}
	/**
	 * 获取：
	 */
	public String getExecutionpolicy() {
		return executionpolicy;
	}
	/**
	 * 设置：
	 */
	public void setActions(String actions) {
		this.actions = actions;
	}
	/**
	 * 获取：
	 */
	public String getActions() {
		return actions;
	}
	/**
	 * 设置：
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * 获取：
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * 设置：
	 */
	public void setCoordexternalid(String coordexternalid) {
		this.coordexternalid = coordexternalid;
	}
	/**
	 * 获取：
	 */
	public String getCoordexternalid() {
		return coordexternalid;
	}
	/**
	 * 设置：
	 */
	public void setLastaction(String lastaction) {
		this.lastaction = lastaction;
	}
	/**
	 * 获取：
	 */
	public String getLastaction() {
		return lastaction;
	}
	/**
	 * 设置：
	 */
	public void setPausetime(String pausetime) {
		this.pausetime = pausetime;
	}
	/**
	 * 获取：
	 */
	public String getPausetime() {
		return pausetime;
	}
	/**
	 * 设置：
	 */
	public void setConcurrency(Integer concurrency) {
		this.concurrency = concurrency;
	}
	/**
	 * 获取：
	 */
	public Integer getConcurrency() {
		return concurrency;
	}
	/**
	 * 设置：
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}
	/**
	 * 获取：
	 */
	public Long getTimeout() {
		return timeout;
	}

	public String getWfId() {
		return wfId;
	}

	public void setWfId(String wfId) {
		this.wfId = wfId;
	}
}

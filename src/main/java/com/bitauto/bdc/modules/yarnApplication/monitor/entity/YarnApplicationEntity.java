package com.bitauto.bdc.modules.yarnApplication.monitor.entity;

import java.io.Serializable;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-24 12:11:41
 */
public class YarnApplicationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//yarn application id 
	private String id;
	//任务所属用户
	private String user;
	//应用任务
	private String name;
	//队列名称
	private String queue;
	//状态
	private String state;
	//最终状态
	private String finalstatus;
	//进度
	private Integer progress;
	//诊断信息
	private String diagnostics;
	//应用类型
	private String applicationtype;
	//
	private Long startedtime;
	//
	private Long finishedtime;
	//占用时间
	private Long elapsedtime;
	//分配内存
	private Long allocatedmb;
	//
	private Integer allocatedvcores;
	//
	private Integer runningContainers;
	//
	private Long memoryseconds;
	//
	private Long vcoreseconds;
	//
	private Long preemptedresourcemb;
	//
	private Long preemptedresourcevcores;
	//
	private Long numnonamcontainerpreempted;
	//
	private Long numamcontainerpreempted;

	/**
	 * 设置：yarn application id 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：yarn application id 
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：任务所属用户
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * 获取：任务所属用户
	 */
	public String getUser() {
		return user;
	}
	/**
	 * 设置：应用任务
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：应用任务
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：队列名称
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}
	/**
	 * 获取：队列名称
	 */
	public String getQueue() {
		return queue;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：最终状态
	 */
	public void setFinalstatus(String finalstatus) {
		this.finalstatus = finalstatus;
	}
	/**
	 * 获取：最终状态
	 */
	public String getFinalstatus() {
		return finalstatus;
	}
	/**
	 * 设置：进度
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	/**
	 * 获取：进度
	 */
	public Integer getProgress() {
		return progress;
	}
	/**
	 * 设置：诊断信息
	 */
	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}
	/**
	 * 获取：诊断信息
	 */
	public String getDiagnostics() {
		return diagnostics;
	}
	/**
	 * 设置：应用类型
	 */
	public void setApplicationtype(String applicationtype) {
		this.applicationtype = applicationtype;
	}
	/**
	 * 获取：应用类型
	 */
	public String getApplicationtype() {
		return applicationtype;
	}

	public Long getStartedtime() {
		return startedtime;
	}

	public void setStartedtime(Long startedtime) {
		this.startedtime = startedtime;
	}

	/**
	 * 设置：
	 */
	public void setFinishedtime(Long finishedtime) {
		this.finishedtime = finishedtime;
	}
	/**
	 * 获取：
	 */
	public Long getFinishedtime() {
		return finishedtime;
	}
	/**
	 * 设置：占用时间
	 */
	public void setElapsedtime(Long elapsedtime) {
		this.elapsedtime = elapsedtime;
	}
	/**
	 * 获取：占用时间
	 */
	public Long getElapsedtime() {
		return elapsedtime;
	}
	/**
	 * 设置：分配内存
	 */
	public void setAllocatedmb(Long allocatedmb) {
		this.allocatedmb = allocatedmb;
	}
	/**
	 * 获取：分配内存
	 */
	public Long getAllocatedmb() {
		return allocatedmb;
	}
	/**
	 * 设置：
	 */
	public void setAllocatedvcores(Integer allocatedvcores) {
		this.allocatedvcores = allocatedvcores;
	}
	/**
	 * 获取：
	 */
	public Integer getAllocatedvcores() {
		return allocatedvcores;
	}
	/**
	 * 设置：
	 */
	public void setRunningContainers(Integer runningContainers) {
		this.runningContainers = runningContainers;
	}
	/**
	 * 获取：
	 */
	public Integer getRunningContainers() {
		return runningContainers;
	}
	/**
	 * 设置：
	 */
	public void setMemoryseconds(Long memoryseconds) {
		this.memoryseconds = memoryseconds;
	}
	/**
	 * 获取：
	 */
	public Long getMemoryseconds() {
		return memoryseconds;
	}
	/**
	 * 设置：
	 */
	public void setVcoreseconds(Long vcoreseconds) {
		this.vcoreseconds = vcoreseconds;
	}
	/**
	 * 获取：
	 */
	public Long getVcoreseconds() {
		return vcoreseconds;
	}
	/**
	 * 设置：
	 */
	public void setPreemptedresourcemb(Long preemptedresourcemb) {
		this.preemptedresourcemb = preemptedresourcemb;
	}
	/**
	 * 获取：
	 */
	public Long getPreemptedresourcemb() {
		return preemptedresourcemb;
	}
	/**
	 * 设置：
	 */
	public void setPreemptedresourcevcores(Long preemptedresourcevcores) {
		this.preemptedresourcevcores = preemptedresourcevcores;
	}
	/**
	 * 获取：
	 */
	public Long getPreemptedresourcevcores() {
		return preemptedresourcevcores;
	}
	/**
	 * 设置：
	 */
	public void setNumnonamcontainerpreempted(Long numnonamcontainerpreempted) {
		this.numnonamcontainerpreempted = numnonamcontainerpreempted;
	}
	/**
	 * 获取：
	 */
	public Long getNumnonamcontainerpreempted() {
		return numnonamcontainerpreempted;
	}
	/**
	 * 设置：
	 */
	public void setNumamcontainerpreempted(Long numamcontainerpreempted) {
		this.numamcontainerpreempted = numamcontainerpreempted;
	}
	/**
	 * 获取：
	 */
	public Long getNumamcontainerpreempted() {
		return numamcontainerpreempted;
	}
}

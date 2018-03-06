package com.bitauto.bdc.modules.oozie.monitor.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-16 13:21:53
 */
public class CoorDatasetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String coordinatorjobid;
	//
	private String dataset;

	private String coordjobname;

	private String wfId;

	/**
	 * 设置：
	 */
	public void setCoordinatorjobid(String coordinatorjobid) {
		this.coordinatorjobid = coordinatorjobid;
	}
	/**
	 * 获取：
	 */
	public String getCoordinatorjobid() {
		return coordinatorjobid;
	}
	/**
	 * 设置：
	 */
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	/**
	 * 获取：
	 */
	public String getDataset() {
		return dataset;
	}

	public String getCoordjobname() {
		return coordjobname;
	}

	public void setCoordjobname(String coordjobname) {
		this.coordjobname = coordjobname;
	}

	public String getWfId() {
		return wfId;
	}

	public void setWfId(String wfId) {
		this.wfId = wfId;
	}
}

package com.bitauto.bdc.modules.config.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-03 10:09:06
 */
public class ConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String key;
	//
	private String value;

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
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：
	 */
	public String getValue() {
		return value;
	}
}

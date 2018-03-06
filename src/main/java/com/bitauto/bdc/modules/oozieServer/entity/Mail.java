package com.bitauto.bdc.modules.oozieServer.entity;

import java.io.Serializable;
import java.util.List;

public class Mail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4453335064153977662L;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private String index;
	private String date;
	private String sql;
	private String table;
	private String info;
	private List<String> list;
}

package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 用户数据保存类
 * 
 * @author
 * 
 */
public class SearchEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 名
	 */
	private String name;
	/**
	 * 介绍
	 */
	private String msg;

	public SearchEntity() {
		setMothed("search");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", name);
		return map;
	}
}

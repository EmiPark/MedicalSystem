package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 删除说说数据
 * 
 * @author
 * 
 */
public class DeleteMessageEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;

	public DeleteMessageEntity() {
		setMothed("deleteMsg");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);
		return map;
	}
}

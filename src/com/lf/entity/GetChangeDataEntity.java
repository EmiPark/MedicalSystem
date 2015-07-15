package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 得到保存的记录数据
 * 
 * @author
 * 
 */
public class GetChangeDataEntity extends BaseConnectEntity {
	/**
	 * 表示1体温；2心跳；3血压；4呼吸频率；5体重;6锻炼;7吃药;8吃饭;9病史
	 */
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public GetChangeDataEntity() {
		setMothed("getChangeData");
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("type", type);
		return map;
	}
}

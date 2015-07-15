package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 用户数据保存类
 * 
 * @author
 * 
 */
public class ChangeEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 数据
	 */
	private String number;
	/**
	 * 表示1体温；2心跳；3血压；4呼吸频率；5体重;6锻炼;7吃药;8吃饭;9病史
	 */
	private int type;
	/**
	 * 备用字段（现在用于吃药记录中的是否痊愈1表示y2表示n）
	 */
	private String remark;

	public ChangeEntity() {
		setMothed("commitData");
		setRemark("-1");
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", time);
		map.put("number", number);
		map.put("type", type);
		map.put("remark", remark);
		return map;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}

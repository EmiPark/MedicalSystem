package com.lf.entity;

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
	/**
	 * 所属科
	 */
	private String sub;
	/**
	 * 治疗
	 */
	private String deal;
	/**
	 * 檢查
	 */
	private String check;

	public SearchEntity() {
		setMothed("search");
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	/**
	 * 首字母
	 */
	private String pre;

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
}

package com.lf.entity;

/**
 * 药瓶保存类
 * 
 * @author
 * 
 */
public class SearchEntity2 extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 名
	 */
	private String name;
	/**
	 * 功效
	 */
	private String gx;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 首字母
	 */
	private String pre;

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public SearchEntity2() {
		setMothed("getMedical");
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

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}

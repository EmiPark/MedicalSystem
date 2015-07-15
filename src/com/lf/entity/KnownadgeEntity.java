package com.lf.entity;


/**
 * 用户数据保存类
 * 
 * @author
 * 
 */
public class KnownadgeEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 图片地址
	 */
	private String url;
	/**
	 * 消息
	 */
	private String message;

	public KnownadgeEntity() {
		setMothed("getKnownadge");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.lf.entity;


/**
 * 资讯数据保存类
 * 
 * @author
 * 
 */
public class MsgEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 文字描述
	 */
	private String msg;
	/**
	 * 图片地址
	 */
	private String url;

	public MsgEntity() {
		setMothed("getAllMsg");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

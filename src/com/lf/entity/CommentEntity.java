package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 评论实体类
 * 
 * @author wzg
 * 
 */
public class CommentEntity extends BaseConnectEntity {
	// id
	private int id;
	// 消息id
	private int messageId;
	// 姓名
	private String name;
	// 评论内容
	private String message;

	public CommentEntity() {
		setMessage("comment");
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("messageId", messageId);
		map.put("name", name);
		map.put("message", message);
		return map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.lf.entity;

import java.util.LinkedHashMap;
import java.util.List;

import android.widget.LinearLayout;

/**
 * 发布信息
 * 
 * @author WZG
 * 
 */
public class MessageEntity extends BaseConnectEntity {
	private int id;
	// 发布者姓名
	private String name;
	// 发布的消息
	private String message;
	// 发布时间
	private String time;
	// 评论内容
	private List<CommentEntity> ltComment;
	// 评论内容显示控件
	private LinearLayout llyout;

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", name);
		map.put("message", message);
		map.put("time", time);
		return map;
	}

	public LinearLayout getLlyout() {
		return llyout;
	}

	public void setLlyout(LinearLayout llyout) {
		this.llyout = llyout;
	}

	public List<CommentEntity> getLtComment() {
		return ltComment;
	}

	public void setLtComment(List<CommentEntity> ltComment) {
		this.ltComment = ltComment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}

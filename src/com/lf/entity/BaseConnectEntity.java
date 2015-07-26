package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 网路交互数据基础类
 * 
 * @author wzg
 * 
 */
public class BaseConnectEntity {
	// 服务器方法
	private String mothed;
	private LinkedHashMap<String, Object> map;
	
	

	public String getMothed() {
		return mothed;
	}

	public void setMothed(String mothed) {
		this.mothed = mothed;
	}

	public void setMap(LinkedHashMap<String, Object> map) {
		this.map = map;
	}

	/**
	 * 服务器接口中需要得到的参数
	 * 
	 * @return
	 */
	public LinkedHashMap<String, Object> getMap() {
		return map;
	}

}

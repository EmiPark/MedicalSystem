package com.lf.entity;

import java.util.LinkedHashMap;

/**
 * 用户数据保存类
 * 
 * @author
 * 
 */
public class UserEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String nameS;
	public String getNameS() {
		return nameS;
	}

	public void setNameS(String nameS) {
		this.nameS = nameS;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAds() {
		return ads;
	}

	public void setAds(String ads) {
		this.ads = ads;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 身高
	 */
	private String high;
	/**
	 * 血型
	 */
	private String xx;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 邮箱地址
	 */
	private String ads;
	/**
	 * 性别
	 */
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", name);
		map.put("password", password);
		return map;
	}
}

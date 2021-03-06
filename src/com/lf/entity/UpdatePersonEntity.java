package com.lf.entity;

import java.util.LinkedHashMap;

import com.lf.common.MyApplication;

/**
 * 用户信息保存类
 * 
 * @author
 * 
 */
public class UpdatePersonEntity extends BaseConnectEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 姓名
	 */
	private String name;
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
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String psd;
	/**
	 * 头像
	 */
	private String photo;

	public UpdatePersonEntity(PersonEntity entity) {
		setMothed("updatePerson");
		setId(MyApplication.userEntity.getId());
		setName(entity.getName());
		setAge(entity.getAge());
		setHigh(entity.getHigh());
		setXx(entity.getXx());
		setTel(entity.getTel());
		setAds(entity.getAds());
		setSex(entity.getSex());
		setUserName(entity.getUserName());
		setPsd(entity.getPsd());
		setPhoto(entity.getPhoto());
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("id", id);
		map.put("nameS", name);
		map.put("age", age);
		map.put("high", high);
		map.put("xx", xx);
		map.put("tel", tel);
		map.put("ads", ads);
		map.put("sex", sex);
		map.put("name", userName);
		map.put("password", psd);
		map.put("photo", photo);
		return map;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
}

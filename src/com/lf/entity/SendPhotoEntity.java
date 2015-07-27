package com.lf.entity;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;
/**
 * 上传图片
 * @author iceman
 *
 */
public class SendPhotoEntity extends BaseConnectEntity {
	private PhotoEntity photo;
	
	public SendPhotoEntity(PhotoEntity entity){
		setMothed("sendPhoto");
		setPhoto(entity);
	}

	@Override
	public LinkedHashMap<String, Object> getMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("photo", JSONObject.toJSONString(photo));
		return map;
	}

	public PhotoEntity getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoEntity photo) {
		this.photo = photo;
	}

}

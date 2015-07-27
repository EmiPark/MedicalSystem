package com.lf.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
/**
 * 图片封装类
 * @author iceman
 *
 */
public class PhotoEntity extends BaseConnectEntity {

	private byte[] photo;

	public PhotoEntity(Bitmap bitmap) {
		setPhoto(getBitmapByte(bitmap));
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * 解析bitmap
	 * 
	 * @param bitmap
	 * @return
	 */
	protected byte[] getBitmapByte(Bitmap bitmap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

}

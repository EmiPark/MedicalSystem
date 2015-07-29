package com.lf.activity;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.common.AsyncImageLoader;
import com.lf.common.MyApplication;
import com.lf.common.RoundImage;
import com.lf.dialog.TimeDialog;
import com.lf.entity.PersonEntity;
import com.lf.entity.PhotoEntity;
import com.lf.entity.SendPhotoEntity;
import com.lf.entity.UpdatePersonEntity;
import com.lf.entity.UserEntity;
import com.lf.web.Global;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 记录个人信息界面
 * 
 * @author wzg
 * 
 */
public class PsersonalActivity extends BaseActivity implements ConnectListener {
	// 姓名；年龄；身高；电话；地址；血型
	private EditText etvName, etvAge, etvHigh, etvTel, etvAds, etvXx, etvUerName, etvPsd;
	private String name, age, high, tel, ads, xx, sex, userName, psd;
	private RoundImage imgPhoto;
	private Bitmap bitmap;
	// 男按钮,女
	private RadioButton rbtB, rbtG;
	// 个人信息类
	private PersonEntity pEntity;
	// 当前用户
	private UserEntity entity;
	// 注册
	private boolean resign;
	// 调用相机
	private final int CAMERA = 1;
	private Connect connect;
	private AsyncImageLoader loader;
	// 跟新用户信息
	private UpdatePersonEntity uPersonEntity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.activity_person_data);
	}

	@Override
	protected void initResource() {
		pEntity = new PersonEntity();
		loader = AsyncImageLoader.getAsyncImageLoader(this);
		resign = getIntent().getExtras().getBoolean("type");
		if (resign) {
			connect = Connect.REGISTER;
			pEntity.setMothed("register");
		} else {
			entity = MyApplication.userEntity;
			pEntity.setId(entity.getId());
			connect = Connect.UPDATE_PERSON;
			pEntity.setMothed("updatePerson");
			entity = MyApplication.userEntity;
		}
	}

	@Override
	protected void initWidget() {
		etvName = (EditText) findViewById(R.id.etv_name);
		etvUerName = (EditText) findViewById(R.id.etv_user_name);
		imgPhoto = (RoundImage) findViewById(R.id.img_hoto);
		etvPsd = (EditText) findViewById(R.id.etv_psd);
		etvAge = (EditText) findViewById(R.id.etv_age);
		etvHigh = (EditText) findViewById(R.id.etv_high);
		etvTel = (EditText) findViewById(R.id.etv_tel);
		etvAds = (EditText) findViewById(R.id.etv_ads);
		etvXx = (EditText) findViewById(R.id.etv_xx);
		rbtB = (RadioButton) findViewById(R.id.rbtn_b);
		rbtG = (RadioButton) findViewById(R.id.rbtn_g);
		Button btn = (Button) findViewById(R.id.btn_ok);
		TextView tvTitle = (TextView) findViewById(R.id.tv_title);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_l_r_boy);
		imgPhoto.setImageBitmap(bitmap);

		if (resign) {
			btn.setText("注册");
			tvTitle.setText("注册");
		} else {
			btn.setText("修改");
			String url = Global.Photo_URL + entity.getPhoto();
			loader.loadDrawable(url, imgPhoto, bitmap);
			etvUerName.setText(entity.getName());
			etvPsd.setText(entity.getPassword());
			etvName.setText(entity.getNameS());
			etvAds.setText(entity.getAds());
			etvAge.setText(entity.getAge());
			etvHigh.setText(entity.getHigh());
			etvXx.setText(entity.getXx());
			etvTel.setText(entity.getTel());
			if (entity.getSex().equals("2")) {
				rbtG.setChecked(true);
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_time:
			new TimeDialog(this, v).show();
			break;
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_ok:
			determinalDo();
			break;
		case R.id.img_hoto:
			setPhotoByCamera();
			break;
		default:
			break;
		}
	}

	/**
	 * 调用相机拍照
	 */
	private void setPhotoByCamera() {
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra("camerasensortype", CameraInfo.CAMERA_FACING_FRONT);
		startActivityForResult(camera, CAMERA);
	}

	/**
	 * 选择图片结束后，回调方法
	 */
	protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
		if (requestCode == CAMERA && resultCode == Activity.RESULT_OK && null != data) {
			// 获取相机返回的数据，并转换为图片格式
			bitmap = data.getParcelableExtra("data");
			imgPhoto.setImageBitmap(bitmap);
		}
	}

	/**
	 * 确认处理
	 */
	private void determinalDo() {
		name = etvName.getText().toString().trim();
		age = etvAge.getText().toString().trim();
		high = etvHigh.getText().toString().trim();
		tel = etvTel.getText().toString().trim();
		ads = etvAds.getText().toString().trim();
		xx = etvXx.getText().toString().trim();
		sex = rbtB.isChecked() ? "1" : "2";
		userName = etvUerName.getText().toString().trim();
		psd = etvPsd.getText().toString().trim();
		if ("".equals(name) || "".equals(age) || "".equals(high) || "".equals(tel) || "".equals(ads) || "".equals(xx)
				|| "".equals(userName) || "".equals(psd)) {
			showMsg(R.string.simple);
			return;
		}
		pEntity.setName(name);
		pEntity.setAds(ads);
		pEntity.setAge(age);
		pEntity.setTel(tel);
		pEntity.setXx(xx);
		pEntity.setSex(sex);
		pEntity.setHigh(high);
		pEntity.setUserName(userName);
		pEntity.setPsd(psd);
		if (bitmap == null) {
			uPersonEntity = new UpdatePersonEntity(pEntity);
			new WebCommonTask(this, this, "上传中。。。。。").execute(this.connect, uPersonEntity);
		} else {
			SendPhotoEntity sEntity = new SendPhotoEntity(new PhotoEntity(bitmap));
			new WebCommonTask(this, this, "上传中。。。。。").execute(Connect.SEND_PHOTO, sEntity);
		}
	}

	@Override
	public void Succes(Connect connect, Object object) {
		switch (connect) {
		case SEND_PHOTO:
			Log.e("tag", "--->" + object.toString());
			pEntity.setPhoto(object.toString());
			if (this.connect == Connect.REGISTER) {
				new WebCommonTask(this, this, "上传中。。。。。").execute(this.connect, pEntity);
			} else {
				uPersonEntity = new UpdatePersonEntity(pEntity);
				new WebCommonTask(this, this, "上传中。。。。。").execute(this.connect, uPersonEntity);
			}
			break;
		case UPDATE_PERSON:
			UserEntity userEntity = new UserEntity();
			userEntity.setMothed("login");
			userEntity.setName(userName);
			userEntity.setPassword(psd);
			new WebCommonTask(this, this, null).execute(Connect.LOGIN, userEntity);
			break;
		case REGISTER:
			showMsg("注册成功");
			finish();
			break;
		case LOGIN:
			MyApplication.userEntity = JSONObject.parseObject(object.toString(), UserEntity.class);
			showMsg("修改成功");
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

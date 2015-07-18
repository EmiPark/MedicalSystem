package com.lf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bysj.zzx.R;
import com.lf.common.MyApplication;
import com.lf.dialog.TimeDialog;
import com.lf.entity.PersonEntity;
import com.lf.entity.UserEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 记录个人信息界面
 * 
 * @author wzg
 * 
 */
public class PsersonalActivity extends BaseActivity implements ConnectListener {
	// 姓名；年龄；身高；电话；地址；血型
	private EditText etvName, etvAge, etvHigh, etvTel, etvAds, etvXx;
	private String name, age, high, tel, ads, xx, sex;
	// 男按钮,女
	private RadioButton rbtB, rbtG;
	// 个人信息类
	private PersonEntity pEntity;
	// 是否存在历史记录
	private boolean isExit;
	// 当前用户
	private UserEntity entity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.activity_person_data);
	}

	@Override
	protected void initResource() {
		pEntity = new PersonEntity();
		entity = MyApplication.userEntity;
		pEntity.setId(entity.getId());
		if (null == MyApplication.userEntity.getNameS()) {
			isExit = false;
		} else {
			isExit = true;
		}
	}

	@Override
	protected void initWidget() {
		etvName = (EditText) findViewById(R.id.etv_name);
		etvAge = (EditText) findViewById(R.id.etv_age);
		etvHigh = (EditText) findViewById(R.id.etv_high);
		etvTel = (EditText) findViewById(R.id.etv_tel);
		etvAds = (EditText) findViewById(R.id.etv_ads);
		etvXx = (EditText) findViewById(R.id.etv_xx);
		rbtB = (RadioButton) findViewById(R.id.rbtn_b);
		rbtG = (RadioButton) findViewById(R.id.rbtn_g);

		if (isExit) {
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
		default:
			break;
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
		if ("".equals(name) || "".equals(age) || "".equals(high)
				|| "".equals(tel) || "".equals(ads) || "".equals(xx)) {
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
		new WebCommonTask(this, this, null).execute(Connect.UPDATE_PERSON,
				pEntity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		entity.setNameS(name);
		entity.setTel(tel);
		entity.setAge(age);
		entity.setAds(ads);
		entity.setXx(xx);
		entity.setSex(sex);
		entity.setHigh(high);
		showMsg("修改成功");
		finish();
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

package com.lf.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.common.MyApplication;
import com.lf.entity.UserEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 主界面登录和注册界面
 * 
 * @author
 * 
 */
public class MainActivity extends BaseActivity implements ConnectListener {
	// * 点击放回键间隔的时间
	private long lgTime;
	// 用户数据尸体类
	private UserEntity userEntity;
	// 用户和密码
	private EditText etvName, etvPsd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.activity_main);
	}

	@Override
	protected void initResource() {
		userEntity = new UserEntity();
		userEntity.setMothed("login");
	}

	@Override
	protected void initWidget() {
		etvName = (EditText) findViewById(R.id.etv_name);
		etvPsd = (EditText) findViewById(R.id.etv_password);
	}

	// @Override登陆和注册监听
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_longin:
			loginDo();
			break;
		case R.id.tv_register:
			Bundle bundle = new Bundle();
			bundle.putBoolean("type", true);
			jumpActivity(PsersonalActivity.class, bundle);
			break;
		default:
			break;
		}
	}

	/**
	 * 登录和注册处理
	 */
	private void loginDo() {
		String name = etvName.getText().toString();
		String psd = etvPsd.getText().toString();
		if ("".equals(name) || "".equals(psd)) {
			showMsg("信息不全");
			return;
		}
		userEntity.setName(name);
		userEntity.setPassword(psd);
		new WebCommonTask(this, this, null).execute(Connect.LOGIN, userEntity);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - lgTime < 2000) {
				android.os.Process.killProcess(android.os.Process.myPid());
			} else {
				Toast.makeText(this, "再点击一次推出程序！", Toast.LENGTH_SHORT).show();
				lgTime = System.currentTimeMillis();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void Succes(final Connect connect, final Object object) {
		MyApplication.userEntity = JSONObject.parseObject(object.toString(),
				UserEntity.class);
		jumpActivity(ControllActivity.class);
		finish();
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

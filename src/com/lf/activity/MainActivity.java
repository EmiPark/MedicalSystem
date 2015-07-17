package com.lf.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
	// 登录选项
	private RadioButton rbtnLogin;
	// 登录按钮
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.activity_main);
	}

	@Override
	protected void initResource() {
		userEntity = new UserEntity();
		userEntity.setMothed("login");
		//
		// userEntity.setName("罗飞");
		// MyApplication.userEntity = userEntity;
		// jumpActivity(ControllActivity.class);
		// finish();
	}

	@Override
	protected void initWidget() {
		etvName = (EditText) findViewById(R.id.etv_name);
		etvPsd = (EditText) findViewById(R.id.etv_password);
		rbtnLogin = (RadioButton) findViewById(R.id.btn_log);
		btnLogin = (Button) findViewById(R.id.btn_longin);
	}

	// @Override登陆和注册监听
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_log:
			userEntity.setMothed("login");
			btnLogin.setText("登录");
			break;
		case R.id.btn_register:
			userEntity.setMothed("register");
			btnLogin.setText("注册");
			break;
		case R.id.btn_longin:
			loginDo();
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
		if (rbtnLogin.isChecked()) {
			new WebCommonTask(this, this, null).execute(Connect.LOGIN,
					userEntity);
		} else {
			new WebCommonTask(this, this, null).execute(Connect.REGISTER,
					userEntity);
		}
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
		switch (connect) {
		case LOGIN:
			MyApplication.userEntity = JSONObject.parseObject(
					object.toString(), UserEntity.class);
			jumpActivity(ControllActivity.class);
			break;
		case REGISTER:
			((RadioButton) findViewById(R.id.btn_log)).setChecked(true);
			showMsg("注册成功");
			userEntity.setMothed("login");
			btnLogin.setText("登录");
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

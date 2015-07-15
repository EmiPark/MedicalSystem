package com.lf.activity;

import com.bysj.zzx.R;
import com.lf.dialog.TimeDialog;
import com.lf.entity.ChangeEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 记录体重界面
 * 
 * @author wzg
 * 
 */
public class TzActivity extends BaseActivity implements ConnectListener {
	// 体重
	private EditText etvNubmer;
	// 时间
	private Button btnTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_tz);
	}

	@Override
	protected void initResource() {

	}

	@Override
	protected void initWidget() {
		etvNubmer = (EditText) findViewById(R.id.etv_number);
		btnTime = (Button) findViewById(R.id.btn_time);
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
		case R.id.btn_history:
			Bundle bundle = new Bundle();
			bundle.putInt("type", 5);
			jumpActivity(LineChartActivity.class, bundle);
		default:
			break;
		}
	}

	/**
	 * 确认处理
	 */
	private void determinalDo() {
		String number = etvNubmer.getText().toString().trim();
		String time = btnTime.getText().toString().trim();
		if ("".equals(number) || "".equals(time)) {
			showMsg(R.string.simple);
			return;
		}
		ChangeEntity entity = new ChangeEntity();
		entity.setNumber(number);
		entity.setTime(time);
		entity.setType(5);
		new WebCommonTask(this, this, null).execute(Connect.COMMIT_CHANGE_DATA,
				entity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		showMsg("成功");
		finish();
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

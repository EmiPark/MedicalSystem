package com.lf.activity;

import com.bysj.zzx.R;
import com.lf.common.UtilManage;
import com.lf.entity.MessageEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 发布消息界面
 * 
 * @author wzg
 * 
 */
public class SendMessageActivity extends BaseActivity implements
		ConnectListener {
//	消息数据
	private MessageEntity entity;
//	消息内容控件
	private EditText etvMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_send_message);
	}

	@Override
	protected void initResource() {
		entity = new MessageEntity();
		entity.setMessage("commitMsg");
	}

	@Override
	protected void initWidget() {
		etvMsg = (EditText) findViewById(R.id.tv_msg);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_ok:
			sendDo();
			break;
		default:
			break;
		}
	}

	/**
	 * 发布说说处理
	 */
	private void sendDo() {
		entity.setTime(UtilManage.getCurrentTimeBy("yyyy-MM-dd HH:mm"));
		String msg = etvMsg.getText().toString();
		if ("".equals(msg)) {
			showMsg(R.string.simple);
			return;
		}
		entity.setMessage(msg);
		new WebCommonTask(this, this, "提交中。。。").execute(Connect.SEND_MSG,
				entity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		showMsg("发布成功");
		finish();
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

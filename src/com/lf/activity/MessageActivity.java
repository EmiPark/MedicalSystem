package com.lf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.MessageAdapter;
import com.lf.entity.BaseConnectEntity;
import com.lf.entity.MessageEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 朋友圈
 * 
 * @author wzg
 * 
 */
public class MessageActivity extends BaseActivity implements ConnectListener {
	private BaseConnectEntity mEntity;
	private MessageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.init(R.layout.activity_message);
	}

	@Override
	protected void initResource() {
		mEntity = new BaseConnectEntity();
		mEntity.setMothed("getAllMeesage");
		adapter = new MessageAdapter(this);
	}

	@Override
	protected void initWidget() {
		ListView lt = (ListView) findViewById(R.id.ltview);
		lt.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new WebCommonTask(this, this, "loading....").execute(
				Connect.GET_ALL_MSG, mEntity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		adapter.setData(JSONObject.parseArray(object.toString(),
				MessageEntity.class));
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_send:
			jumpActivity(SendMessageActivity.class);
			break;
		case R.id.btn_back:
			finish();
			break;
		default:
			break;
		}
	}

}

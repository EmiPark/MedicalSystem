package com.lf.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.NormalHistoryAdatapter;
import com.lf.entity.ChangeEntity;
import com.lf.entity.GetChangeDataEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 一般波动数据历史记录展示页面
 * 
 * @author wzg
 * 
 */
public class NormalHistoryActivity extends BaseActivity implements
		ConnectListener {
	// 类型1体温；2心跳；3血压；4呼吸频率；5体重;6锻炼;7吃药;8吃饭;9病史
	private int type;
	// 标题
	private String title;
	// 数据封装
	private NormalHistoryAdatapter adatapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_normal_history);
	}

	@Override
	protected void initResource() {
		type = getIntent().getExtras().getInt("type");
		title = getResources().getStringArray(R.array.title)[type];
		adatapter = new NormalHistoryAdatapter(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		GetChangeDataEntity entity = new GetChangeDataEntity();
		entity.setType(type);
		// 获取当前类型的历史信息数据
		new WebCommonTask(this, this, "load......").execute(
				Connect.GET_CHANGE_DATA, entity);
	}

	@Override
	protected void initWidget() {
		((TextView) findViewById(R.id.tv_title)).setText(title);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		adatapter.setData(JSONObject.parseArray(object.toString(),
				ChangeEntity.class));
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

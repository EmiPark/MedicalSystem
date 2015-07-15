package com.lf.activity;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.entity.ChangeEntity;
import com.lf.entity.GetChangeDataEntity;
import com.lf.view.PathView;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 记录折线图显示界面
 * 
 * @author wzg
 * 
 */
public class LineChartActivity extends BaseActivity implements ConnectListener {
	// y轴最大值
	private int yMax;
	// x轴数据
	private String[] xData;
	// x对应的点数据
	private int[] xPoint;
	// 自定义这线图
	private PathView pw;
	// 标题
	private String title;
	// 类型1体温；2心跳；3血压；4呼吸频率；5体重;6锻炼;7吃药;8吃饭;9病史
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_linechat);
	}

	@Override
	protected void initResource() {
		type = getIntent().getExtras().getInt("type");
		title = getResources().getStringArray(R.array.title)[type];
		GetChangeDataEntity entity = new GetChangeDataEntity();
		entity.setType(type);
//		获取当前类型的历史信息数据
		new WebCommonTask(this, this, "load......").execute(
				Connect.GET_CHANGE_DATA, entity);
	}

	@Override
	protected void initWidget() {
		pw = (PathView) this.findViewById(R.id.linechat);
		((TextView) findViewById(R.id.tv_title)).setText(title);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		List<ChangeEntity> list = JSONObject.parseArray(object.toString(),
				ChangeEntity.class);
		xPoint = new int[list.size()];
		xData = new String[list.size()];
		for (int index = 0; index < list.size(); index++) {
			ChangeEntity entity = list.get(index);
			xData[index] = entity.getTime();
			int y = Integer.valueOf(entity.getNumber());
			xPoint[index] = y;
			yMax = yMax < y ? y : yMax;
		}
		pw.setXCount(yMax, 10);
		pw.setX(xData);
		pw.setDate(xPoint);
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

	@Override
	public void onClick(View v) {
		finish();
	}
}

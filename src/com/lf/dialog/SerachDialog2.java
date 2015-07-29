package com.lf.dialog;

import com.bysj.zzx.R;
import com.lf.entity.SearchEntity2;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 收索结果界面
 * 
 * @author wzg
 * 
 */
public class SerachDialog2 extends BaseDialog {
	// 数据
	private SearchEntity2 entity;
	// 名;主題;信息介紹；處理方法/檢查方法
	private TextView tvName, tvGx, tvPrice;

	public SerachDialog2(Context context, SearchEntity2 entity) {
		super(context, R.style.dialog_ransparent);
		this.entity = entity;
		init(R.layout.dialog_search2);
	}

	@Override
	protected void initResource() {

	}

	@Override
	protected void initWidget() {
		tvName = (TextView) findViewById(R.id.tv_name);
		tvGx = (TextView) findViewById(R.id.tv_gx);
		tvPrice = (TextView) findViewById(R.id.tv_price);
		((Button) findViewById(R.id.btn_back)).setOnClickListener(this);

		tvName.setText(entity.getName());
		tvGx.setText("功效：" + entity.getGx());
		tvPrice.setText("价格：" + entity.getPrice());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		dismiss();
	}

}

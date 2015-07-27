package com.lf.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.entity.SearchEntity;

/**
 * 收索结果界面
 * 
 * @author wzg
 * 
 */
public class SerachDialog extends BaseDialog {
	// 数据
	private SearchEntity entity;
	// 名;主題;信息介紹；處理方法/檢查方法
	private TextView tvName, tvSub, tvMsg, tvDeal, tvCheck;

	public SerachDialog(Context context, SearchEntity entity) {
		super(context, R.style.dialog_ransparent);
		this.entity = entity;
		init(R.layout.dialog_search);
	}

	@Override
	protected void initResource() {

	}

	@Override
	protected void initWidget() {
		tvName = (TextView) findViewById(R.id.tv_name);
		tvSub = (TextView) findViewById(R.id.tv_sub);
		tvMsg = (TextView) findViewById(R.id.tv_msg);
		tvDeal = (TextView) findViewById(R.id.tv_zl);
		tvCheck = (TextView) findViewById(R.id.tv_jc);
		((Button) findViewById(R.id.btn_back)).setOnClickListener(this);

		tvName.setText(entity.getName());
		tvSub.setText(entity.getSub());
		tvMsg.setText(entity.getMsg());
		tvDeal.setText(entity.getDeal());
		tvCheck.setText(entity.getCheck());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		dismiss();
	}

}

package com.lf.dialog;

import android.content.Context;
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
	//数据
	private SearchEntity entity;

	public SerachDialog(Context context, SearchEntity entity) {
		super(context);
		this.entity = entity;
		init(R.layout.dialog_search);
	}

	@Override
	protected void initResource() {
	}

	@Override
	protected void initWidget() {
		((TextView) findViewById(R.id.tv_title)).setText(entity.getName());
		((TextView) findViewById(R.id.tv_msg)).setText(entity.getMsg());
	}

}

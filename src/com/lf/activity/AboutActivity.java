package com.lf.activity;

import com.bysj.zzx.R;

import android.os.Bundle;
import android.view.View;

/**
 * 关于界面
 * 
 * @author iceman
 * 
 */
public class AboutActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_about);
	}

	@Override
	protected void initResource() {

	}

	@Override
	protected void initWidget() {

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		finish();
	}
}

package com.lf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.common.MyApplication;
import com.lf.fragment.BaseFragment;
import com.lf.fragment.ContentFragment;
import com.lf.fragment.KnownageFragment;
import com.lf.fragment.MsgFragment;
import com.lf.view.DragLayout;
import com.lf.view.DragLayout.DragListener;

/**
 * 主界面内容显示
 * 
 * @author wzg
 * 
 */
public class ControllActivity extends BaseFramentActivity implements
		DragListener {
	// 内容；知识；资讯
	private BaseFragment cFragment, sFragment, mFragment;
	// 可拖动侧边栏控件
	private DragLayout dl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_controller);
	}

	@Override
	protected void initResource() {
		mFragment = new MsgFragment();
		sFragment = new KnownageFragment();
		cFragment = new ContentFragment();
	}

	@Override
	protected void initWidget() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setLeftRange(240);
		dl.setDragListener(this);
		setFragment(R.id.rlyout_content, cFragment);
		((TextView) findViewById(R.id.tv_name))
				.setText(MyApplication.userEntity.getName());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_user:
			dl.open();
			break;
		case R.id.add:
			jumpActivity(MessageActivity.class);
			break;
		case R.id.rbtn_health:
			setFragment(R.id.rlyout_content, cFragment);
			break;
		case R.id.rbtn_knownadge:
			setFragment(R.id.rlyout_content, sFragment);
			break;
		case R.id.rbtn_tj:
			setFragment(R.id.rlyout_content, mFragment);
			break;
		case R.id.person:
			jumpActivity(PsersonalActivity.class);
			break;
		case R.id.about:
			jumpActivity(AboutActivity.class);
			break;
		case R.id.btn_foot:
			jumpActivity(RecordFootActivity.class);
			break;
		case R.id.exit:
			android.os.Process.killProcess(android.os.Process.myPid());
			break;
		default:
			break;
		}
	}

	@Override
	public void onOpen() {

	}

	@Override
	public void onClose() {

	}

	@Override
	public void onDrag(float percent) {

	}

}

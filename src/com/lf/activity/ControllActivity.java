package com.lf.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.common.AsyncImageLoader;
import com.lf.common.MyApplication;
import com.lf.common.RoundImage;
import com.lf.fragment.BaseFragment;
import com.lf.fragment.ContentFragment;
import com.lf.fragment.FriendFragment;
import com.lf.fragment.KnownageFragment;
import com.lf.fragment.MsgFragment;
import com.lf.view.DragLayout;
import com.lf.view.DragLayout.DragListener;
import com.lf.web.Global;

/**
 * 主界面内容显示
 * 
 * @author wzg
 * 
 */
public class ControllActivity extends BaseFramentActivity implements DragListener {
	// 内容；知识；资讯
	private BaseFragment cFragment, sFragment, mFragment, fFragment;
	// 可拖动侧边栏控件
	private DragLayout dl;
	// 用户名
	private TextView tvName;
	// 头像
	private RoundImage image;

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
		fFragment = new FriendFragment();
	}

	@Override
	protected void initWidget() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setLeftRange(240);
		dl.setDragListener(this);
		setFragment(R.id.rlyout_content, cFragment);
		tvName = ((TextView) findViewById(R.id.tv_name));
		image = (RoundImage) findViewById(R.id.photo);
		image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_l_r_boy));
	}

	@Override
	protected void onResume() {
		super.onResume();
		tvName.setText(MyApplication.userEntity.getName());
		AsyncImageLoader loader = AsyncImageLoader.getAsyncImageLoader(this);
		loader.loadDrawable(Global.Photo_URL + MyApplication.userEntity.getPhoto(), image);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_user:
			dl.open();
			break;
		case R.id.add:
			jumpActivity(SendMessageActivity.class);
			break;
		case R.id.rbtn_health:
			setFragment(R.id.rlyout_content, cFragment);
			break;
		case R.id.friend:
			setFragment(R.id.rlyout_content, fFragment);
			break;
		case R.id.rbtn_knownadge:
			setFragment(R.id.rlyout_content, sFragment);
			break;
		case R.id.rbtn_tj:
			setFragment(R.id.rlyout_content, mFragment);
			break;
		case R.id.person:
			Bundle bundle = new Bundle();
			bundle.putBoolean("type", false);
			jumpActivity(PsersonalActivity.class, bundle);
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

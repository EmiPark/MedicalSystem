package com.lf.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bysj.zzx.R;

/**
 * fangmentactivity 自定义基础类
 * 
 * @author WZG
 * 
 */
public abstract class BaseFramentActivity extends FragmentActivity implements
		OnClickListener {

	private final String TAG = "BaseFramentActivity";
	// fragment操作相关类
	private FragmentManager fragmentManager;

	private int layout;

	public void onCreate(Bundle savedInstanceState, int layout) {
		// 设置横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		fragmentManager = getSupportFragmentManager();
		super.onCreate(savedInstanceState);
	}

	/**
	 * 初始化
	 */
	protected void init(int layout) {
		try {
			fragmentManager = getSupportFragmentManager();
			setContentView(layout);
			initResource();
			initWidget();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	/**
	 * 初始化数据
	 */
	protected abstract void initResource();

	/**
	 * 初始化控件
	 */
	protected abstract void initWidget();

	/**
	 * 初始化固有控件
	 */
	protected void initConstantWidget() {

	}

	/**
	 * 显示基本信息
	 * 
	 * @param msg
	 */
	protected void showMsg(int msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示基本信息
	 * 
	 * @param msg
	 */
	protected void showMsg(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
	}

	/**
	 * 跳转到指定的activity
	 * 
	 * @param context
	 * @param cls
	 */
	public void jumpActivity(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	/**
	 * 带参数跳转到指定的activity
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public void jumpActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(this, cls);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 设置当前tab里显示的fragment页面
	 * 
	 * @param fragment
	 */
	protected void setFragment(int layout, Fragment fragment) {
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fade_out);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(layout, fragment);
		ft.commit();
	}
}

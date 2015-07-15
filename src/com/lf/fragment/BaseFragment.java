package com.lf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * 自定义fragment基础类
 * 
 * @author WZG
 * 
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	private final String TAG = "BaseFragment";

	/**
	 * 初始化
	 */
	protected void init(View view) {
		try {
			initResource(view);
			initWidget(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据
	 */
	protected abstract void initResource(View view);

	/**
	 * 初始化控件
	 */
	protected abstract void initWidget(View view);

	/**
	 * 初始化基本数据
	 */
	protected void initNormalData() {

	}

	/**
	 * 初始化固有控件
	 */
	protected void initConstantWidget(View view) {

	}

	/**
	 * 显示基本信息
	 * 
	 * @param msg
	 */
	protected void showMsg(int msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示基本信息
	 * 
	 * @param msg
	 */
	protected void showMsg(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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
	public void jumpActivity(Class cls) {
		Intent intent = new Intent(getActivity(), cls);
		startActivity(intent);
	}

	/**
	 * 带参数跳转到指定的activity
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public void jumpActivity(Class cls, Bundle bundle) {
		Intent intent = new Intent(getActivity(), cls);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}

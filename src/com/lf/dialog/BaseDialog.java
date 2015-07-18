package com.lf.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

/**
 * Dialog基础类
 * 
 * @author WZG
 * 
 */
public abstract class BaseDialog extends Dialog implements OnClickListener {
	private final String TAG = "BaseDialog";

	public BaseDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
	}

	public BaseDialog(Context context, int style) {
		super(context, style);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
	}

	/**
	 * 初始化
	 */
	protected void init(int layout) {
		try {
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
		Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示基本信息
	 * 
	 * @param msg
	 */
	protected void showMsg(String msg) {
		Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
		Intent intent = new Intent(getContext(), cls);
		getContext().startActivity(intent);
	}

	/**
	 * 带参数跳转到指定的activity
	 * 
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public void jumpActivity(Class cls, Bundle bundle) {
		Intent intent = new Intent(getContext(), cls);
		intent.putExtras(bundle);
		getContext().startActivity(intent);
	}
}

package com.lf.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.lf.entity.BaseConnectEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;

/**
 * 网络连接异步
 * 
 * @author
 * 
 */
public class WebCommonTask extends AsyncTask<Object, Object, Object> {
	// 上下关系
	private Context context;
	// 交互进度框
	private ProgressDialog progressDialog;
	// 交互数据类
	private WebCommonFashion webFashion;
	// 类型
	private Connect connect;
	// 网络交互监听
	private ConnectListener listener;

	public WebCommonTask(Context context, ConnectListener listener,
			String message) {
		this.context = context;
		this.listener = listener;
		this.webFashion = new WebCommonFashion(context);
		if (null != message && !"".equals(message)) {
			setDialog(message);
		}
	}

	/**
	 * 开始交互之前
	 */
	protected void onPreExecute() {
		super.onPreExecute();
		if (progressDialog != null) {
			progressDialog.show();
		}
	}

	/**
	 * 交互中
	 */
	protected Object doInBackground(Object... params) {
		if (!isWifiConnected()) {
			return "noWIFI";
		}
		connect = (Connect) params[0];
		return webFashion.connectWebService(connect,
				(BaseConnectEntity) params[1]);
	}

	/**
	 * 交互结束
	 */
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (isSuccesed(result)) {
			Log.e("tag", "--->" + result.toString());
			listener.Succes(connect, result);
		}
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	/**
	 * 返回数据是否正确
	 * 
	 * @param object
	 * @return
	 */
	private boolean isSuccesed(Object object) {
		if (null == object) {
			listener.Failed("失败");
			return false;
		}
		if (object.toString().indexOf("failed") > 0) {
			String[] msg = object.toString().split("&");
			listener.Failed(msg[1]);
			return false;
		}
		if ("noWIFI".equals(object.toString())) {
			listener.Failed("网络出错了！");
			return false;
		}
		return true;
	}

	/**
	 * 判断网络状态
	 * 
	 * @return
	 */
	private boolean isWifiConnected() {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWiFiNetworkInfo = mConnectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWiFiNetworkInfo != null) {
			return mWiFiNetworkInfo.isAvailable();
		}
		return false;
	}

	/**
	 * 设在progress弹出框家摘框
	 * 
	 * @param value
	 * @return
	 */
	private void setDialog(String value) {
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(value);
		progressDialog.setCancelable(false);
	}

}

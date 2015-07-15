package com.lf.web;

import java.util.LinkedHashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.util.Log;

import com.lf.entity.BaseConnectEntity;
import com.lf.web.Global.Connect;

/**
 * 交互网络数据类
 * 
 * @author wzg
 * 
 */
public class WebCommonFashion {
	private Context context;

	public WebCommonFashion(Context context) {
		this.context = context;
	}

	/**
	 * 服务器交互方法
	 * 
	 * @param msgId
	 * @return
	 */
	public Object connectWebService(Connect connect, BaseConnectEntity entity) {
		Object object = getWebServiceValue(context, entity.getMothed(),
				entity.getMap());
		return object;
	}

	/**
	 * 
	 * @param methodName
	 *            访问的方法名
	 * @param name
	 *            参数名
	 * @param obj
	 *            参数内容
	 * @param objV
	 *            需要返回的对象,如果返回值是List类型添null
	 * @param objL
	 *            需要返回的List,如果返回值是对象类型添null
	 * @param listType
	 *            常见的接口实现所有Java类型,Type listType = new
	 *            TypeToken<ArrayList<需要的对象类型>>() {
	 *            }.getType();，,如果返回值是对象类型添null
	 * @return
	 */
	private static Object getWebServiceValue(Context context,
			String methodName, LinkedHashMap<String, Object> map) {
		SoapObject rpc = new SoapObject("http://service.ws.edu.com", methodName);
		if (map != null) {
			for (String dataKey : map.keySet()) {
				rpc.addProperty(dataKey, map.get(dataKey));
			}
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = rpc;
		envelope.dotNet = true;
		envelope.encodingStyle = "UTF-8";
		envelope.setOutputSoapObject(rpc);
		try {
			Log.e("tag", "ip--->" + Global.SERVICE_URL + "  mothed-->"
					+ methodName);
			HttpTransportSE ht = new HttpTransportSE(Global.SERVICE_URL, 10000);
			ht.call(methodName, envelope);
			Object obResulte = envelope.getResponse();
			return obResulte;
		} catch (Exception e) {
			System.out.println("异常~~~");
			return null;
		}
	}

	/**
	 * 返回数据是否正确
	 * 
	 * @param object
	 * @return
	 */
	private boolean isSuccesed(Object object) {
		if (null == object || "failed".equals(object.toString())) {
			return false;
		}
		return true;
	}

}

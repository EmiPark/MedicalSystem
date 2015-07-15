package com.lf.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.MsgAdatapter;
import com.lf.entity.MsgEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 资讯
 * 
 * @author wzg
 * 
 */
public class MsgFragment extends BaseFragment implements ConnectListener {
	// 信息
	private MsgEntity msgEntity;
	// 数据
	private MsgAdatapter adatapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_msg, container, false);
		super.init(view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		new WebCommonTask(getActivity(), this, "loading....").execute(
				Connect.GET_ALL_IMSG, msgEntity);
	}

	@Override
	protected void initResource(View view) {
		msgEntity = new MsgEntity();
		adatapter = new MsgAdatapter(getActivity());
	}

	@Override
	protected void initWidget(View view) {
		ListView ltView = (ListView) view.findViewById(R.id.lt);
		ltView.setAdapter(adatapter);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		List<MsgEntity> list = JSONObject.parseArray(object.toString(),
				MsgEntity.class);
		adatapter.setData(list);
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

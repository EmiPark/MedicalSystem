package com.lf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.KonwnadgeAdatapter;
import com.lf.dialog.SerachDialog;
import com.lf.entity.KnownadgeEntity;
import com.lf.entity.SearchEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 知识界面
 * 
 * @author wzg
 * 
 */
public class KnownageFragment extends BaseFragment implements ConnectListener {
	// 收索内容
	private EditText etvSearch;
	// 信息封装类
	private KonwnadgeAdatapter adatapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_knownadge, container,
				false);
		super.init(view);
		return view;
	}

	@Override
	protected void initResource(View view) {
		adatapter = new KonwnadgeAdatapter(getActivity());
	}

	@Override
	public void onResume() {
		super.onResume();
		KnownadgeEntity entity = new KnownadgeEntity();
		new WebCommonTask(getActivity(), this, "loading....").execute(
				Connect.GET_ALL_KNOW, entity);
	}

	@Override
	protected void initWidget(View view) {
		etvSearch = (EditText) view.findViewById(R.id.etv_search);
		((Button) view.findViewById(R.id.btn_search)).setOnClickListener(this);
		ListView lView = (ListView) view.findViewById(R.id.lt);
		lView.setAdapter(adatapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			searchDo();
			break;
		default:
			break;
		}
	}

	/**
	 * 收索处理
	 */
	private void searchDo() {
		String search = etvSearch.getText().toString();
		if (search.equals("")) {
			showMsg(R.string.simple);
			return;
		}
		SearchEntity entity = new SearchEntity();
		entity.setName(search);
		new WebCommonTask(getActivity(), this, "search....").execute(
				Connect.SEARCH, entity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		switch (connect) {
		case SEARCH:
			SearchEntity entity = JSONObject.parseObject(object.toString(),
					SearchEntity.class);
			etvSearch.setText("");
			new SerachDialog(getActivity(), entity).show();
			break;
		case GET_ALL_KNOW:
			adatapter.setData(JSONObject.parseArray(object.toString(),
					KnownadgeEntity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public void Failed(String message) {
		etvSearch.setText("");
		showMsg(message);
	}

}

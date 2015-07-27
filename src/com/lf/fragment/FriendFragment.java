package com.lf.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.MessageAdapter;
import com.lf.entity.BaseConnectEntity;
import com.lf.entity.CommentEntity;
import com.lf.entity.MessageEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 朋友圈
 * 
 * @author wzg
 * 
 */
public class FriendFragment extends BaseFragment implements ConnectListener {
	private BaseConnectEntity mEntity;
	private MessageAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_message, container,
				false);
		super.init(view);
		return view;
	}

	@Override
	protected void initResource(View view) {
		mEntity = new BaseConnectEntity();
		mEntity.setMothed("getAllMeesage");
		adapter = new MessageAdapter(getActivity());
	}

	@Override
	protected void initWidget(View view) {
		ListView lt = (ListView) view.findViewById(R.id.ltview);
		lt.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		new WebCommonTask(getActivity(), this, "loading....").execute(
				Connect.GET_ALL_MSG, mEntity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		JSONObject jObject = JSONObject.parseObject(object.toString());
		List<MessageEntity> ltEntities = JSONObject.parseArray(
				jObject.getString("msg"), MessageEntity.class);
		for (MessageEntity entity : ltEntities) {
			List<CommentEntity> lCommentEntities = JSONObject.parseArray(
					jObject.getString(String.valueOf(entity.getId())),
					CommentEntity.class);
			entity.setLtComment(lCommentEntities);
		}
		adapter.setData(ltEntities);
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

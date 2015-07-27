package com.lf.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONObject;
import com.bysj.zzx.R;
import com.lf.adapter.MsgAdatapter;
import com.lf.adapter.ViewPagerAdapter;
import com.lf.common.AsyncImageLoader;
import com.lf.entity.IntreduceEntity;
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
public class MsgFragment extends BaseFragment implements ConnectListener,
		OnPageChangeListener {
	// 信息
	private MsgEntity msgEntity;
	// 数据
	private MsgAdatapter adatapter;
	private AsyncImageLoader loader;
	private ViewPager vPager;
	// 当前推荐也控件
	private RadioGroup rGroup;
	private int currentPosition;

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
		currentPosition = 0;
		adatapter = new MsgAdatapter(getActivity());
		loader = AsyncImageLoader.getAsyncImageLoader(getActivity());
		new WebCommonTask(getActivity(), this, null).execute(
				Connect.GET_INTREDUCE, new IntreduceEntity());
	}

	@Override
	protected void initWidget(View view) {
		ListView ltView = (ListView) view.findViewById(R.id.lt);
		ltView.setAdapter(adatapter);
		rGroup = (RadioGroup) view.findViewById(R.id.rgoup);
		((RadioButton) rGroup.getChildAt(currentPosition)).setChecked(true);
		vPager = (ViewPager) view.findViewById(R.id.vp_intreduce);
		vPager.setOnPageChangeListener(this);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		switch (connect) {
		case GET_ALL_IMSG:
			List<MsgEntity> list = JSONObject.parseArray(object.toString(),
					MsgEntity.class);
			adatapter.setData(list);
			break;
		case GET_INTREDUCE:
			List<IntreduceEntity> list2 = JSONObject.parseArray(
					object.toString(), IntreduceEntity.class);
			List<View> ltViews = new ArrayList<View>();
			for (IntreduceEntity entity : list2) {
				ImageView imageView = new ImageView(getActivity());
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				loader.loadDrawable(entity.getUrl(), imageView);
				ltViews.add(imageView);
			}
			ViewPagerAdapter adapter = new ViewPagerAdapter(ltViews);
			vPager.setAdapter(adapter);
			handler.sendEmptyMessageDelayed(1000, 10);
			break;
		default:
			break;
		}
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			currentPosition++;
			currentPosition = currentPosition == 5 ? 0 : currentPosition;
			vPager.setCurrentItem(currentPosition);
			handler.sendEmptyMessageDelayed(1000, 4000);
		}
	};

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		currentPosition = arg0;
		((RadioButton) rGroup.getChildAt(arg0)).setChecked(true);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		handler.removeMessages(1000);
	}

}

package com.lf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bysj.zzx.R;
import com.lf.adapter.ContentBtnAdapter;

/**
 * 信息监控和录入界面
 * 
 * @author wzg
 * 
 */
public class ContentFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_content, container,
				false);
		super.init(view);
		return view;
	}

	@Override
	protected void initResource(View view) {
		
	}

	@Override
	protected void initWidget(View view) {
		GridView gView = (GridView) view.findViewById(R.id.gd);
		ContentBtnAdapter adapter = new ContentBtnAdapter(getActivity());
		gView.setAdapter(adapter);
	}

}

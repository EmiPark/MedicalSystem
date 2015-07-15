package com.lf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bysj.zzx.R;
import com.lf.activity.BsActivity;
import com.lf.activity.CfActivity;
import com.lf.activity.CyActivity;
import com.lf.activity.DlActivity;
import com.lf.activity.HxActivity;
import com.lf.activity.TwActivity;
import com.lf.activity.TzActivity;
import com.lf.activity.XtActivity;
import com.lf.activity.XyActivity;
import com.lf.adapter.ContentBtnAdapter;
import com.lf.adapter.ContentBtnAdapter.OnItemClick;

/**
 * 信息监控和录入界面
 * 
 * @author wzg
 * 
 */
public class ContentFragment extends BaseFragment implements OnItemClick {

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
		ContentBtnAdapter adapter = new ContentBtnAdapter(getActivity(), this);
		gView.setAdapter(adapter);
	}

	@Override
	public void click(int position) {
		switch (position) {
		case 0:
			jumpActivity(TwActivity.class);
			break;
		case 1:
			jumpActivity(XtActivity.class);
			break;
		case 2:
			jumpActivity(XyActivity.class);
			break;
		case 3:
			jumpActivity(HxActivity.class);
			break;
		case 4:
			jumpActivity(TzActivity.class);
			break;
		case 5:
			jumpActivity(DlActivity.class);
			break;
		case 6:
			jumpActivity(CyActivity.class);
			break;
		case 7:
			jumpActivity(BsActivity.class);
			break;
		case 8:
			jumpActivity(CfActivity.class);
			break;
		default:
			break;
		}
	}

}

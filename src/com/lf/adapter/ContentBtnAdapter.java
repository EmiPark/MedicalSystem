package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.activity.BsActivity;
import com.lf.activity.CfActivity;
import com.lf.activity.CyActivity;
import com.lf.activity.DlActivity;
import com.lf.activity.HxActivity;
import com.lf.activity.LineChartActivity;
import com.lf.activity.NormalHistoryActivity;
import com.lf.activity.TwActivity;
import com.lf.activity.TzActivity;
import com.lf.activity.XtActivity;
import com.lf.activity.XyActivity;

/**
 * 内容界面按钮封装
 * 
 * @author WZG
 * 
 */
public class ContentBtnAdapter extends BaseAdapter {
	// 上下文
	private Context context;
	private List<String> ltData;
	private List<Integer> ltDataSrc;
	private List<Class> ltClasses;

	public ContentBtnAdapter(Context context) {
		ltData = new ArrayList<String>();
		ltDataSrc = new ArrayList<Integer>();
		ltClasses = new ArrayList<Class>();
		ltData.add("体温");
		ltDataSrc.add(R.drawable.bg_tw);
		ltClasses.add(TwActivity.class);

		ltData.add("心跳");
		ltDataSrc.add(R.drawable.bg_xt);
		ltClasses.add(XtActivity.class);

		ltData.add("血压");
		ltDataSrc.add(R.drawable.bg_xy);
		ltClasses.add(XyActivity.class);

		ltData.add("呼吸");
		ltDataSrc.add(R.drawable.bg_hx);
		ltClasses.add(HxActivity.class);

		ltData.add("体重");
		ltDataSrc.add(R.drawable.bg_tw);
		ltClasses.add(TzActivity.class);

		ltData.add("锻炼");
		ltDataSrc.add(R.drawable.bg_tz);
		ltClasses.add(DlActivity.class);

		ltData.add("用药");
		ltDataSrc.add(R.drawable.bg_yy);
		ltClasses.add(CyActivity.class);
		
		ltData.add("饮食");
		ltDataSrc.add(R.drawable.bg_ys);
		ltClasses.add(CfActivity.class);

		ltData.add("病史");
		ltDataSrc.add(R.drawable.bg_bs);
		ltClasses.add(BsActivity.class);

		this.context = context;
	}

	@Override
	public int getCount() {
		return ltData.size();
	}

	@Override
	public Object getItem(int position) {
		return ltData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// 加载控件
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adpter_content_item,
					null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.btn = (Button) convertView.findViewById(R.id.btn_history);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(ltData.get(position));
		holder.img.setBackgroundResource(ltDataSrc.get(position));
		MyListener listener = new MyListener(holder, position);
		holder.img.setOnClickListener(listener);
		holder.tvName.setOnClickListener(listener);
		holder.btn.setOnClickListener(listener);

		return convertView;
	}

	/**
	 * 每一行控件的帮助类
	 * 
	 * @author iceman
	 * 
	 */
	private class ViewHolder {
		private ImageView img;
		private TextView tvName;
		private Button btn;
	}

	class MyListener implements OnClickListener {
		private ViewHolder holder;
		private int postion;

		MyListener(ViewHolder holder, int position) {
			this.holder = holder;
			this.postion = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.img:
			case R.id.tv_name:
				jumpActivity(ltClasses.get(postion));
				break;
			case R.id.btn_history:
				Bundle bundle = new Bundle();
				bundle.putInt("type", postion+1);
				if (postion <= 4) {
					jumpActivity(LineChartActivity.class, bundle);
				} else {
					jumpActivity(NormalHistoryActivity.class, bundle);
				}
				break;
			default:
				break;
			}
		}

		/**
		 * 跳转到指定的activity
		 * 
		 * @param context
		 * @param cls
		 */
		public void jumpActivity(Class cls) {
			Intent intent = new Intent(context, cls);
			context.startActivity(intent);
		}
		
		/**
		 * 带参数跳转到指定的activity
		 * 
		 * @param context
		 * @param cls
		 * @param bundle
		 */
		public void jumpActivity(Class cls, Bundle bundle) {
			Intent intent = new Intent(context, cls);
			intent.putExtras(bundle);
			context.startActivity(intent);
		}
	}

}

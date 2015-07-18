package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.entity.ChangeEntity;

/**
 * 基本浮动数据显示封装类
 * 
 * @author wzg
 * 
 */
public class NormalHistoryAdatapter extends BaseAdapter {
	private List<ChangeEntity> ltData;
	private Context context;

	public NormalHistoryAdatapter(Context convertView) {
		ltData = new ArrayList<ChangeEntity>();
		this.context = convertView;
	}

	/**
	 * 设置数据
	 * 
	 * @param ltData
	 */
	public void setData(List<ChangeEntity> ltData) {
		this.ltData = ltData;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return ltData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder;
		if (convertView == null) {
			holder = new Viewholder();
			convertView = View.inflate(context, R.layout.rlyout_normal_item,
					null);
			holder.tvFirst = (TextView) convertView
					.findViewById(R.id.tv_firtst);
			holder.tvRemark = (TextView) convertView
					.findViewById(R.id.tv_remark);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}

		ChangeEntity entity = ltData.get(position);
		String state = entity.getRemark().equals("1") ? "痊愈" : "未痊愈";
		if (entity.getRemark().equals("-1")) {
			holder.tvRemark.setVisibility(View.GONE);
		} else {
			holder.tvRemark.setText(state);
		}
		holder.tvFirst.setText("名：" + entity.getNumber());
		holder.tvTime.setText("时间：" + entity.getTime());

		return convertView;
	}

	private class Viewholder {
		private TextView tvFirst;
		private TextView tvRemark;
		private TextView tvTime;

	}

}

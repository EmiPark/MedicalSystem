package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
			holder.tv = (TextView) convertView.findViewById(R.id.tv_msg);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}

		ChangeEntity entity = ltData.get(position);
		holder.tv.setText(entity.getNumber() + "   " + entity.getRemark()
				+ "    " + entity.getTime());
		return convertView;
	}

	private class Viewholder {
		private TextView tv;
	}

}

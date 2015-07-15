package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.bysj.zzx.R;

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
	private OnItemClick onItemClick;

	public ContentBtnAdapter(Context context, OnItemClick onItemClick) {
		ltData = new ArrayList<String>();
		ltData.add("体温");
		ltData.add("心跳");
		ltData.add("血压");
		ltData.add("呼吸");
		ltData.add("体重");
		ltData.add("锻炼");
		ltData.add("用药");
		ltData.add("病史");
		ltData.add("饮食");
		this.onItemClick = onItemClick;
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
			convertView = View.inflate(context, R.layout.btn_common_content,
					null);
			holder.btn = (Button) convertView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.btn.setText(ltData.get(position));

		holder.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onItemClick.click(position);
			}
		});
		return convertView;
	}

	/**
	 * 每一行控件的帮助类
	 * 
	 * @author iceman
	 * 
	 */
	private class ViewHolder {
		private Button btn;
	}

	/**
	 * 点击监听
	 * 
	 * @author wzg
	 * 
	 */
	public interface OnItemClick {
		void click(int position);
	}

}

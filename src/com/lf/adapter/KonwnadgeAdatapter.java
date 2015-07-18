package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.common.AsyncImageLoader;
import com.lf.entity.KnownadgeEntity;

/**
 * 知识封装类
 * 
 * @author wzg
 * 
 */
public class KonwnadgeAdatapter extends BaseAdapter {
	private List<KnownadgeEntity> ltData;
	private Context context;
	// 图片下载和缓存处理
	private AsyncImageLoader loader;

	public KonwnadgeAdatapter(Context convertView) {
		ltData = new ArrayList<KnownadgeEntity>();
		loader = AsyncImageLoader.getAsyncImageLoader(context);
		this.context = convertView;
	}

	/**
	 * 设置数据
	 * 
	 * @param ltData
	 */
	public void setData(List<KnownadgeEntity> ltData) {
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
			convertView = View.inflate(context, R.layout.rlyout_msg_item, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_intreduce);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		KnownadgeEntity entity = ltData.get(position);
		if (null == entity.getUrl()) {
			holder.img.setVisibility(View.GONE);
		} else {
			loader.loadDrawable(entity.getUrl(), holder.img);
		}
		holder.tv.setText(entity.getMessage());
		return convertView;
	}

	private class Viewholder {
		private ImageView img;
		private TextView tv;
	}

}

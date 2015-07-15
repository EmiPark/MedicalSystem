package com.lf.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.dialog.commentDialog;
import com.lf.entity.CommentEntity;
import com.lf.entity.MessageEntity;

/**
 * 朋友圈说说信息封装
 * 
 * @author WZG
 * 
 */
public class MessageAdapter extends BaseAdapter {
	// 数据
	private List<MessageEntity> ltData;
	// 上下文
	private Context context;

	public MessageAdapter(Context context) {
		ltData = new ArrayList<MessageEntity>();
		this.context = context;
	}

	/**
	 * 设在数据
	 * 
	 * @param ltData
	 */
	public void setData(List<MessageEntity> ltData) {
		this.ltData = ltData;
		notifyDataSetChanged();
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
		return ltData.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		// 加载控件
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.relyout_item, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvMessage = (TextView) convertView
					.findViewById(R.id.tv_message);
			holder.tvDelete = (TextView) convertView
					.findViewById(R.id.tv_delete);
			holder.tvComment = (TextView) convertView
					.findViewById(R.id.tv_comment);
			holder.ltView = (LinearLayout) convertView
					.findViewById(R.id.lt_comment);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		setListener(position, holder);
		setData(position, holder);
		return convertView;
	}

	/**
	 * 设置监听
	 * 
	 * @param entity
	 * @param holder
	 */
	private void setListener(int position, ViewHolder holder) {
		MyListener listener = new MyListener(position);
		holder.tvDelete.setOnClickListener(listener);
		holder.tvComment.setOnClickListener(listener);
	}

	/**
	 * 设置数据到控件中
	 * 
	 * @param entity
	 * @param holder
	 */
	private void setData(int postion, ViewHolder holder) {
		MessageEntity entity = ltData.get(postion);
		entity.setLlyout(holder.ltView);
		holder.tvName.setText(entity.getName() + "  时间：" + entity.getTime());
		holder.tvMessage.setText("说说：" + entity.getMessage());
		List<CommentEntity> mEntities = new ArrayList<CommentEntity>();
		holder.ltView.removeAllViews();
		for (CommentEntity entityC : mEntities) {
			View view = View.inflate(context, R.layout.rlyout_comment_item,
					null);
			holder.ltView.addView(view);
			TextView tvMessage = (TextView) view.findViewById(R.id.tv_name);
			// 设置评论人姓名（截取小部分）+评论
			tvMessage.setText(entityC.getName() + ":" + entityC.getMessage());
		}
	}

	/**
	 * 更具不同用户显示不同的界面
	 */
	// private void showUi(int postion, ViewHolder holder) {
	// MessageEntity entity = ltData.get(postion);
	// if (entity.getName().equals(MyApplication.userEntity.)) {
	// holder.tvDelete.setVisibility(View.VISIBLE);
	// } else {
	// holder.tvDelete.setVisibility(View.GONE);
	// }
	// }

	/**
	 * 自定义点击监听器
	 * 
	 * @author iceman
	 * 
	 */
	private class MyListener implements OnClickListener {
		private MessageEntity mEntity;

		private MyListener(int position) {
			this.mEntity = ltData.get(position);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_delete:
				deleteDo();
				break;
			case R.id.tv_comment:
				new commentDialog(context, mEntity).show();
				break;
			default:
				break;
			}
		}

		/**
		 * 删除处理
		 */
		private void deleteDo() {
			// 确认对话框
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("删除说说");
			builder.setMessage("您是否删除当前说说？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create();
			builder.show();
		}

	}

	/**
	 * 每一行控件的帮助类
	 * 
	 * @author iceman
	 * 
	 */
	private class ViewHolder {
		// 发布人名
		private TextView tvName;
		// 发布的说说
		private TextView tvMessage;
		// 评论按钮
		private TextView tvComment;
		// 删除按钮
		private TextView tvDelete;
		// 评论的listview
		private LinearLayout ltView;
	}

}

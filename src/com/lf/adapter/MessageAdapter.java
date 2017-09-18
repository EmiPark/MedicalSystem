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
import android.widget.Toast;

import com.bysj.zzx.R;
import com.lf.common.AsyncImageLoader;
import com.lf.common.MyApplication;
import com.lf.common.RoundImage;
import com.lf.dialog.commentDialog;
import com.lf.entity.CommentEntity;
import com.lf.entity.DeleteMessageEntity;
import com.lf.entity.MessageEntity;
import com.lf.web.Global;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.WebCommonTask;

/**
 * 朋友圈说说信息封装
 * 
 * @author WZG
 * 
 */
public class MessageAdapter extends BaseAdapter implements ConnectListener {
	// 数据
	private List<MessageEntity> ltData;
	// 上下文
	private Context context;
	// 当前点击位置
	private int currentPosition = -1;
	private AsyncImageLoader loader;

	public MessageAdapter(Context context) {
		ltData = new ArrayList<MessageEntity>();
		this.context = context;
		loader = AsyncImageLoader.getAsyncImageLoader(context);
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
			holder.imagePhoto = (RoundImage) convertView
					.findViewById(R.id.img_user);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		setListener(position, holder);
		setData(position, holder);
		showUi(position, holder);
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
		loader.loadDrawable(Global.Photo_URL + entity.getPhoto(),
				holder.imagePhoto);
		List<CommentEntity> mEntities = entity.getLtComment();
		holder.ltView.removeAllViews();
		for (CommentEntity entityC : mEntities) {
			View view = View.inflate(context, R.layout.rlyout_comment_item,
					null);
			view.setId(entityC.getId());
			holder.ltView.addView(view);
			TextView tvMessage = (TextView) view.findViewById(R.id.tv_name);
			// 设置评论人姓名（截取小部分）+评论
			tvMessage.setText(entityC.getName() + ":" + entityC.getMessage());
			RoundImage photoC = (RoundImage) view.findViewById(R.id.photo);
			loader.loadDrawable(Global.Photo_URL + entityC.getPhoto(), photoC);
		}
	}

	/**
	 * 更具不同用户显示不同的界面
	 */
	private void showUi(int postion, ViewHolder holder) {
		MessageEntity entity = ltData.get(postion);
		if (entity.getName().equals(MyApplication.userEntity.getName())) {
			holder.tvDelete.setVisibility(View.VISIBLE);
		} else {
			holder.tvDelete.setVisibility(View.GONE);
		}
	}

	/**
	 * 自定义点击监听器
	 * 
	 * @author iceman
	 * 
	 */
	private class MyListener implements OnClickListener {
		private MessageEntity mEntity;

		private MyListener(int position) {
			currentPosition = position;
			this.mEntity = ltData.get(position);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_delete:
				DeleteMessageEntity entity = new DeleteMessageEntity();
				entity.setId(mEntity.getId());
				deleteDo(entity);
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
		private void deleteDo(final DeleteMessageEntity entity) {
			// 确认对话框
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("删除说说");
			builder.setMessage("您是否删除当前说说？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							new WebCommonTask(context, MessageAdapter.this,
									"删除。。。。").execute(Connect.DL_MSG, entity);
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
		// 頭像
		private RoundImage imagePhoto;
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

	@Override
	public void Succes(Connect connect, Object object) {
		ltData.remove(currentPosition);
		notifyDataSetChanged();
	}

	@Override
	public void Failed(String message) {
		Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
	}

}

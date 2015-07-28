package com.lf.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bysj.zzx.R;
import com.lf.common.AsyncImageLoader;
import com.lf.common.MyApplication;
import com.lf.common.RoundImage;
import com.lf.entity.CommentEntity;
import com.lf.entity.MessageEntity;
import com.lf.web.Global.Connect;
import com.lf.web.Global.ConnectListener;
import com.lf.web.Global;
import com.lf.web.WebCommonTask;

/**
 * 评价界面
 * 
 * @author wzg
 * 
 */
public class commentDialog extends BaseDialog implements ConnectListener {
	// 评论的内容
	private EditText etvComment;
	// 确定按钮
	private Button btnOk;
	// 消息
	private MessageEntity entity;
	private CommentEntity cEntity;

	public commentDialog(Context context, final MessageEntity entity) {
		super(context);
		this.entity = entity;
		super.init(R.layout.dialog_comment);
	}

	@Override
	protected void initResource() {
		cEntity = new CommentEntity();
	}

	@Override
	protected void initWidget() {
		etvComment = (EditText) findViewById(R.id.etv_comment);
		btnOk = (Button) findViewById(R.id.btn_determinal);
		btnOk.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		String content = etvComment.getText().toString();
		if ("".equals(content)) {
			showMsg("内容不能为空！");
			return;
		} else {
			commnetDo();
		}
	}

	/**
	 * 提交评论处理
	 */
	private void commnetDo() {
		String content = etvComment.getText().toString();
		cEntity.setMessage(content);
		cEntity.setMessageId(entity.getId());
		cEntity.setName(MyApplication.userEntity.getName());
		entity.getLtComment().add(cEntity);
		new WebCommonTask(getContext(), this, null).execute(Connect.COMMENT,
				cEntity);
	}

	@Override
	public void Succes(Connect connect, Object object) {
		showMsg("评论成功！");
		View view = View.inflate(getContext(), R.layout.rlyout_comment_item,
				null);
		entity.getLlyout().addView(view);
		TextView tvMessage = (TextView) view.findViewById(R.id.tv_name);
		// 设置评论人姓名（截取小部分）+评论
		tvMessage.setText(cEntity.getName() + ":" + cEntity.getMessage());
		RoundImage photoC = (RoundImage) view.findViewById(R.id.photo);
		AsyncImageLoader loader = AsyncImageLoader
				.getAsyncImageLoader(getContext());
		loader.loadDrawable(
				Global.Photo_URL + MyApplication.userEntity.getPhoto(), photoC);
		dismiss();
	}

	@Override
	public void Failed(String message) {
		showMsg(message);
	}

}

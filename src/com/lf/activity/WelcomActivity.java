package com.lf.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.bysj.zzx.R;

/**
 * 欢迎界面
 * 
 * @author wzg
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WelcomActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(R.layout.activity_welcom);
	}

	@Override
	protected void initResource() {
		// 延迟进入主界面
		handler.sendEmptyMessageDelayed(1000, 5000);
	}

	@Override
	protected void initWidget() {
		TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
		addAnimation(tvMsg);
	}

	/**
	 * 多动画路线图
	 * 
	 * @param view
	 */
	private void addAnimation(View view) {
		float[] vaules = new float[] { 0.1f, 0, 3f, 0.5f, 0.7f, 0.9f, 1.1f,
				1.3f, 1.5f, 1.3f, 1.1f, 1.0f };
		AnimatorSet set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),
				ObjectAnimator.ofFloat(view, "translationY", 200, 0f),
				ObjectAnimator.ofFloat(view, "translationX", 200, 0f),
				ObjectAnimator.ofFloat(view, "rotationY", 0, 360),
				ObjectAnimator.ofFloat(view, "scaleY", vaules));
		set.setDuration(4000);
		set.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			jumpActivity(MainActivity.class);
			finish();
		}
	};
}

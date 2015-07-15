package com.lf.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bysj.zzx.R;

/**
 * 时间选择框
 * 
 * @author wzg
 * 
 */
public class TimeDialog extends BaseDialog {
	// 触发控件
	private Button btn;
	// 日期控件
	private DatePicker picker;
	// 时间控件
	private TimePicker tPicker;

	public TimeDialog(Context context, View btn) {
		super(context);
		this.btn = (Button) btn;
		init(R.layout.dialog_choose_time);
	}

	@Override
	protected void initResource() {

	}

	@Override
	protected void initWidget() {
		((Button) findViewById(R.id.btn_ok)).setOnClickListener(this);
		picker = (DatePicker) findViewById(R.id.datePick1);
		tPicker = (TimePicker) findViewById(R.id.timer);
		tPicker.setIs24HourView(true);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		int y = picker.getYear();
		int m = picker.getMonth() + 1;
		int d = picker.getDayOfMonth();
		int h = tPicker.getCurrentHour();
		int ms = tPicker.getCurrentMinute();
		btn.setText(y + "-" + m + "-" + d + " " + h + ":" + ms);
		dismiss();
	}

}

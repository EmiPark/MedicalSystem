package com.lf.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

/**
 * 全局公共帮助工具类
 * 
 * @author zzx
 * 
 */
public class UtilManage {

	/**
	 * 通过图片名得到图片在工程中的id
	 * 
	 * @param context
	 * @param imageName
	 * @return
	 */
	public static int getResource(Context context, String imageName) {
		int resId = context.getResources().getIdentifier(imageName, "drawable",
				context.getPackageName());
		return resId;
	}

	/**
	 * 得到当前的时间按照一定的规则
	 * 
	 * @param strRule
	 *            yyyy年MM月dd日 hh：mm
	 * @return
	 */
	public static String getCurrentTimeBy(String strTimeRule) {
		SimpleDateFormat formatter = new SimpleDateFormat(strTimeRule);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	/**
	 * 得到系统的资源文件给定id的string
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	public String getResourceStr(Context context, int id) {
		return context.getResources().getString(id);
	}

	/**
	 * 显示格式如 "yyyy-MM-dd HH:mm:ss" 以指定格式获取当前时间
	 * 
	 * @param type
	 *            显示的格式
	 * 
	 */
	public static String getCurrentTime(String type) {
		String time = "";
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(type, Locale.CHINA);
		time = format.format(date);
		return time;
	}
}

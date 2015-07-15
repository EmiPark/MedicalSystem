package com.lf.common;

import java.util.List;

import android.app.Application;

import com.lf.entity.ChangeEntity;
import com.lf.entity.UserEntity;

/**
 * 全局的静态常量
 * 
 * @author
 * 
 */
public class MyApplication extends Application {
	// 当前登陆的用户
	public static UserEntity userEntity;
	//记录数据
	public static List<ChangeEntity> listChart;
}

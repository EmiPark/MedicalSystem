package com.lf.entity;

public interface IGSWebService {

	/**
	 * 注册
	 * 
	 * @param name
	 * @param password
	 * @param stateLine
	 * @return
	 */
	String register(String name, String password);

	/**
	 * 登录
	 * 
	 * @param name
	 * @param password
	 * @param stateLine
	 * @return
	 */
	String login(String name, String password);

	/**
	 * 提交评论
	 * 
	 * @param messageId
	 * @param name
	 * @param message
	 * @return
	 */
	String comment(int messageId, String name, String message);

	/**
	 * 发布说说
	 * @param name
	 * @param message
	 * @param time
	 * @return
	 */
	String commitMsg(String name, String message, String time);

	/**
	 * 得到全部的消息
	 * 
	 * @return
	 */
	String getAllMeesage();

	/**
	 * 得到推荐的资讯数据
	 * 
	 * @return
	 */
	String getAllMsg();

	/**
	 * 根据内容搜索疾病库数据
	 * 
	 * @param name
	 * @return
	 */
	String search(String name);

	/**
	 * 得到全部的知识数据
	 * 
	 * @return
	 */
	String getKnownadge();

	/**
	 * 提交个人习惯信息数据
	 * 
	 * @param time
	 * @param number
	 * @param type
	 * @param remark
	 * @return
	 */
	String commitData(String time, String number, int type, String remark);

	/**
	 * 修改个人基本信息
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param high
	 * @param xx
	 * @param tel
	 * @param ads
	 * @param sex
	 * @return
	 */
	String updatePerson(int id, String name, String age, String high,
			String xx, String tel, String ads, String sex);

	/**
	 * 得到当前类型的历史数据记录 取最近的7次记录；防止显示溢出
	 * 
	 * @param type
	 * @return
	 */
	String getChangeData(int type);

	/**
	 * 更具id删除说说
	 * 
	 * @param id
	 * @return
	 */
	String deleteMsg(int id);
}

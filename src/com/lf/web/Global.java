package com.lf.web;


/**
 * 网络交互工具类
 * 
 * @author Daibw
 * 
 */
public class Global {
	// 服务器链接地址，其中ip地址是电脑中ip地址随机分配的所以链接之前需要查看本机中ip
	public static String SERVICE_URL = "http://172.20.10.4:8080/GraduationServer/services/GraduationServerWS";
	public static String Photo_URL = "http://172.20.10.4:8080/GraduationServer/services/upload/";
	

	// 请求标识
	public static enum Connect {
		// 登录；注册；提交评论;得到全部消息;得到资讯数据;收索；得到所有的知识数据;提交体温等动态数据;修改个人信息;得到信息记录数据;删除说说;发布说说
		LOGIN, REGISTER, COMMENT, GET_ALL_MSG, GET_ALL_IMSG, SEARCH, GET_ALL_KNOW, COMMIT_CHANGE_DATA, UPDATE_PERSON, GET_CHANGE_DATA, DL_MSG, SEND_MSG,
		GET_INTREDUCE,
	};

	/**
	 * 网络交互结果监听
	 * 
	 * @author wzg
	 * 
	 */
	public interface ConnectListener {
		/**
		 * 成功返回数据
		 * 
		 * @param connect
		 *            請求類型
		 * @param object
		 *            返回數據
		 */
		void Succes(Connect connect, Object object);

		/**
		 * 链接失败
		 */
		void Failed(String message);
	}
}

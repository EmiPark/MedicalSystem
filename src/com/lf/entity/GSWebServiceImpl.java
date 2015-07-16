package com.lf.entity;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import android.widget.SlidingDrawer;

import com.alibaba.fastjson.JSONObject;
import com.edu.entity.DingDanEntity;
import com.edu.entity.HuoWuEntity;
import com.edu.entity.UserEntity;
import com.edu.ws.service.IGSWebService;
import com.google.gson.JsonObject;

@Transactional
@Service
public class GSWebServiceImpl implements IGSWebService {

	// log日志统一属性
	protected Logger logger = Logger.getLogger(GSWebServiceImpl.class);
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getSession() throws HibernateException {
		// 事务必须是开启的，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String register(String name, String password) {
		if ("failed".equals(login(name, password))) {
			getSession().createSQLQuery(
					"insert into user(name,password) values" + "('" + name
							+ "','" + password + "')").executeUpdate();
			return "success";
		} else {
			return "failed&用户已经注册了！";
		}
	}

	@Override
	public String login(String name, String password) {
		List a = getSession()
				.createSQLQuery(
						"select * from user where name='" + name
								+ "' and password='" + password)
				.addEntity(UserEntity.class).list();
		if (a.size() > 0) {
			return JSONObject.toJSONString(a.get(0)).toString();
		} else {
			return "failed&不存在該用戶";
		}
	}

	@Override
	public String comment(int messageId, String name, String message) {
		getSession().createSQLQuery(
				"insert into comment(messageId,name,message) values" + "("
						+ messageId + ",'" + name + "','" + message + "')")
				.executeUpdate();
		return "success";
	}

	@Override
	public String commitMsg(String name, String message, String time) {
		getSession().createSQLQuery(
				"insert into message(name,message,time) values" + "('" + name
						+ "','" + message + "','" + time + "')")
				.executeUpdate();
		return "success";
	}

	@Override
	public String getAllMeesage() {
		List a = getSession().createSQLQuery("select * from message")
				.addEntity(MessageEntity.class).list();
		if (a.size() > 0) {
			for (int index = 0; index < a.size(); index++) {
				MessageEntity entity = (MessageEntity) a.get(index);
				entity.setLtComment(getCommentEntities(entity.getId()));
			}
			return JSONObject.toJSONString(a);
		} else {
			return "failed&该用户没有发布过内容";
		}
	}

	/**
	 * 得到说说下面全部的评论数据
	 * 
	 * @param messageId
	 * @return
	 */
	private List<CommentEntity> getCommentEntities(int messageId) {
		List a = getSession()
				.createSQLQuery(
						"select * from comment where messageId='" + messageId)
				.addEntity(CommentEntity.class).list();
		return a;
	}

	@Override
	public String getAllMsg() {
		List a = getSession().createSQLQuery("select * from msg")
				.addEntity(MsgEntity.class).list();
		if (a.size() > 0) {
			return JSONObject.toJSONString(a);
		} else {
			return "failed&暂时还没有最新的资讯";
		}
	}

	@Override
	public String search(String name) {
		List a = getSession()
				.createSQLQuery(
						"select * from search where name like %" + name + "%")
				.addEntity(SearchEntity.class).list();
		if (a.size() > 0) {
			return JSONObject.toJSONString(a);
		} else {
			return "failed&暂时还没有该疾病的信息";
		}
	}

	@Override
	public String getKnownadge() {
		List a = getSession().createSQLQuery("select * from knownadge ")
				.addEntity(KnownadgeEntity.class).list();
		if (a.size() > 0) {
			return JSONObject.toJSONString(a);
		} else {
			return "failed&暂时还没有知识数据";
		}
	}

	@Override
	public String commitData(String time, String number, int type, String remark) {
		getSession().createSQLQuery(
				"insert into change(time,number,type,remark) values" + "('"
						+ time + "','" + number + "'," + type + ",'" + remark
						+ "')").executeUpdate();
		return "success";
	}

	@Override
	public String updatePerson(int id, String name, String age, String high,
			String xx, String tel, String ads, String sex) {
		String sql = "update user set nameS=" + name + ",age=" + age + ",high="
				+ high + ",xx=" + xx + ",tel=" + tel + ",ads" + ads + ",sex"
				+ sex;
		getSession().createSQLQuery(sql).executeUpdate();
		return "success";
	}

	@Override
	public String getChangeData(int type) {
		List a = getSession()
				.createSQLQuery("select * from change where type=" + type)
				.addEntity(ChangeEntity.class).list();
		if (a.size() > 0) {
			return JSONObject.toJSONString(a);
		} else {
			return "failed&暂时还没有数据";
		}
	}

	@Override
	public String deleteMsg(int id) {
		String sql = "delete from message where id=" + id;
		getSession().createSQLQuery(sql).executeUpdate();
		return "success";
	}

}

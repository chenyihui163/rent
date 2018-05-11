package com.rent.shiro;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;



public class MysessionListenr implements SessionListener{

	private static Log logger =LogFactory.getLog(MysessionListenr.class);

	public void onStart(Session session) {
		// TODO Auto-generated method stub
		logger.info("会话创建：" + session.getId()+"》》时间："+session.getLastAccessTime());  
	}

	public void onStop(Session session) {
		// TODO Auto-generated method stub
		logger.info("会话过期：" + session.getId()+"》》时间："+new Date());  
	}

	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		logger.info("会话停止：" + session.getId()+"》》时间："+session.getLastAccessTime());  
	}

}

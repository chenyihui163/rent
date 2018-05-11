package com.rent.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rent.redis.RedisKeys;
import com.rent.redis.RedisService;

/**
 * Session提供CRUD并进行持久化的一个shiro组件，将集成redis缓存进行开发
 * 
 * @author chenyihui 2018年3月25日
 */
@Component
public class RedisShiroSessionDao extends EnterpriseCacheSessionDAO {

	@Autowired
	private RedisService redisService;

	/**
	 * 创建session
	 */
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		// 组合session key
		final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
		// 存放登录会话session

		setShiroSession(key, session);
		return sessionId;
	}

	/**
	 * 获取session
	 */
	protected Session doReadSession(Serializable sessionId) {
		Session session = super.doReadSession(sessionId);
		if (session == null) {
			final String key = RedisKeys.getShiroSessionKey(sessionId
					.toString());

			session = getShiroSession(key);

		}
		return session;
	}

	/**
	 * 更新session
	 */
	protected void doUpdate(Session session) {
		// does nothing - parent class persists to cache.
		super.doUpdate(session);
		final String key = RedisKeys.getShiroSessionKey(session.getId()
				.toString());

		setShiroSession(key, session);

	}

	// 删除session
	protected void doDelete(Session session) {
		super.doDelete(session);
		final String key = RedisKeys.getShiroSessionKey(session.getId()
				.toString());
		

		redisService.remove(key);

	}

	private Session getShiroSession(String key) {

		
		return (Session) redisService.get(key);
	}

	private void setShiroSession(String key, Session session) {
		// 60分钟后过期
		redisService.set(key, session, Long.valueOf(3600));
		
	}

}

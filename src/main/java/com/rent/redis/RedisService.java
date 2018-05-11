package com.rent.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("unchecked")
public class RedisService {
	private static final Logger logger = LoggerFactory
			.getLogger(RedisService.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/*
	 * 访问计数 ++1
	 * 
	 * @Title: setLock
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param @param key
	 * 
	 * @param @param value
	 * 
	 * @param @return 设定文件
	 * 
	 * @return boolean 返回类型
	 * 
	 * @throws
	 */
	public boolean computeLoginCount( String key) {
		boolean result = false;
		try {
			ValueOperations<String, String> opsForValue = stringRedisTemplate
					.opsForValue();

			opsForValue.increment("login" + key, 1);
			// 访问一次，计数一次
			System.err.println(Integer.parseInt(opsForValue.get("login" + key)));

			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计数
	* @Title: getLoginCount   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param key
	* @param @return    设定文件   
	* @return boolean    返回类型   
	* @throws
	 */
	public boolean getLoginCount( String key,Integer n)
	{
		boolean result =false;
		
		try {
			ValueOperations<String, String> opsForValue = stringRedisTemplate
					.opsForValue();

			if (Integer.parseInt(opsForValue.get("login" + key)) >= n) {
				opsForValue.set("login" + key, "LOCK");
				
				result =true;
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * ，设置用户被锁定一个小时
	 * 
	 * @Title: setLock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param key
	 * @param @param value
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean bindLock( String key, Integer time) {
		boolean result = false;
		try {
			ValueOperations<String, String> opsForValue = stringRedisTemplate
					.opsForValue();
			stringRedisTemplate.expire("login" + key, time, TimeUnit.HOURS);
			if ("LOCK".equals(opsForValue.get("login" + key))) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 清空登陆计数
	 * 
	 * @Title: unbindLock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param key
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean unbindLock( String key) {
		boolean result = false;
		try {
			ValueOperations<String, String> opsForValue = stringRedisTemplate
					.opsForValue();
			opsForValue.set("login" + key, "0");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存设置时效时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			redisTemplate.delete(keys);
		}

	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate
				.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hmSet(String key, Object field, Object value) {
		@SuppressWarnings("unused")
		HashOperations<String, Object, Object> hash = redisTemplate
				.opsForHash();
		logger.info("设置Redis的Key：" + key + "，filed：" + field + "，value："
				+ value);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param field
	 * @return 空值返回null
	 */
	public Object hmGet(String key, Object field) {
		HashOperations<String, Object, Object> hash = redisTemplate
				.opsForHash();
		Object val = hash.get(key, field);
		if (val == null) {
			logger.error("获取key为：" + key + "，field为：" + field + "，的缓存失败");
		}
		return val;
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public void lPush(String k, Object v) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(k, v);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<Object> lRange(String k, long l, long l1) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return list.range(k, l, l1);
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, Object value, double scoure) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}

	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.rangeByScore(key, scoure, scoure1);
	}
}

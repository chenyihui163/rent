package com.rent.redis;

/**
 * Created by lihaohang on 2018/3/14.
 */
public class RedisKeys {
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}

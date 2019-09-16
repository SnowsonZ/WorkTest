package com.test.test.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import redis.clients.jedis.Jedis;

/**
 * @author Snowson
 * @since 2019/9/16 17:45
 */
public class JedisUtil {
    private Jedis jedis;
    private static volatile JedisUtil instance;

    public static JedisUtil getInstance(String host, int port) {
        if (instance == null) {
            synchronized (JedisUtil.class) {
                if (instance == null) {
                    instance = new JedisUtil(host, port);
                }
            }
        }
        return instance;
    }

    private JedisUtil(String host, int port) {
        jedis = new Jedis(host, port);
    }

    public <T> T get(String key, Class<T> clazz) {
        String ret = jedis.get(key);
        if (Strings.isNullOrEmpty(ret)) {
            return null;
        }
        return JSON.parseObject(ret, clazz);
    }

    public <T> T hget(String key, String field, Class<T> clazz) {
        String ret = jedis.hget(key, field);
        if (Strings.isNullOrEmpty(ret)) {
            return null;
        }
        return JSON.parseObject(ret, clazz);
    }
}

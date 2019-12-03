package com.test.test.redis;

import com.test.test.util.JedisUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/4 11:31
 * @Description:
 */
@Slf4j
public class RedisTest {

    private static final String HOST = "172.17.162.177";
    private static final int PORT = 6379;
    private static final String PASSWORD = "riiladmin";

    public static void main(String[] args) {
        JedisUtil instance = JedisUtil.getInstance(HOST, PORT, PASSWORD);
        instance.get("key", Object.class);
    }
}

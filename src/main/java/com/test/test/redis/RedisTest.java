package com.test.test.redis;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Author: Snowson
 * @Date: 2019/1/4 11:31
 * @Description:
 */
@Slf4j
public class RedisTest {
    public static void main(String[] args) {
        int hour = LocalDateTime.now().getHour();
        Jedis jedis = new Jedis("172.17.161.98", 6379);
//        Long result = jedis.exists("flink:dynamic_threshold1_dns",
//                "flink:dynamic_threshold_tcp",
//                "flink:dynamic_threshold_http");
//        log.info("result: {}", result);
        ArrayList<String> agent = new ArrayList<>();
        agent.add("172.15.1.2");
        agent.add("172.15.2.2");
        agent.add("172.15.3.2");
        agent.add("172.15.4.2");
        ImmutableSet<String> agentUniq = ImmutableSet.copyOf(agent);
        String result = Joiner.on(',').join(agentUniq);
        jedis.set("flink:agent", result);
        boolean b = CharMatcher.anyOf("172.15.3.2").matchesAllOf("172.15.3.2, 172.15.3.2");
//        log.info("b: {}", b);
//        String result = jedis.get("flink:agent");
//        log.info("result: {}", result);


    }

    class Redis {

    }
}

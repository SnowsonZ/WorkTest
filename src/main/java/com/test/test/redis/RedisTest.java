package com.test.test.redis;

import com.alibaba.fastjson.JSON;
import com.test.test.redis.bean.AreaInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Author: Snowson
 * @Date: 2019/1/4 11:31
 * @Description:
 */
@Slf4j
public class RedisTest {
    static Jedis jedis = new Jedis("172.17.162.102", 6379);
    public static void main(String[] args) {

        String value = get("mem:userapp-53860982-5686-11e9-8fcd-02420a0a0a05");
        log.info("value: {}", value);
//        String obj = jedis.hget("business:alarm_strategy", "a0375540-510b-11e9-81f5-02420a0a0a05");
//        Integer httpResDelay = JSON.parseObject(obj).getInteger("httpResDelay");
//        log.info("httpResDelay: {}", httpResDelay);
//        Long result = jedis.exists("flink:dynamic_threshold1_dns",
//                "flink:dynamic_threshold_tcp",
//                "flink:dynamic_threshold_http");
//        log.info("result: {}", result);
//        ArrayList<String> agent = new ArrayList<>();
//        agent.add("172.15.1.2");
//        agent.add("172.15.2.2");
//        agent.add("172.15.3.2");
//        agent.add("172.15.4.2");
//        ImmutableSet<String> agentUniq = ImmutableSet.copyOf(agent);
//        String result = Joiner.on(',').join(agentUniq);
//        jedis.set("flink:agent", result);
//        boolean b = CharMatcher.anyOf("172.15.3.2").matchesAllOf("172.15.3.2, 172.15.3.2");
//        log.info("b: {}", b);
//        String result = jedis.get("flink:agent");
//        log.info("result: {}", result);
    }

    public static String get(String key) {
        return jedis.get(key);
    }

    public static  void containsNotLeaf() {
        ArrayList<String> areaBlackList = new ArrayList<String>() {{
            add("234324");
            add("天津");
            add("天津无线");
            add("测试部位置");
        }};
        int hour = LocalDateTime.now().getHour();
        String key = "mem:location-*";
        List<String> areaLists = jedis.mget(jedis.keys("mem:location-*").toArray(new String[]{}));
        AtomicBoolean hasNotLeaf = new AtomicBoolean(false);
        if (areaLists != null && areaLists.size() > 0) {
            areaLists.forEach(str -> {
                AreaInfo area = JSON.parseObject(str, AreaInfo.class);
                if (areaBlackList.contains(area)) {
                    hasNotLeaf.set(true);
                }
            });
            log.info("main, hasNotLeaf: {}", hasNotLeaf);
        }else {
            log.info("main, get key: {} from redis is null", key);
        }
    }
}

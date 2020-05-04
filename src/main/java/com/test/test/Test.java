package com.test.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.ImmutableMap;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.test.test.layering.UserVO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author Snowson
 * @since 2019/3/29 15:52
 */
@Slf4j
public class Test {

    private static final Double NUM_FLAG=100.6d;
    public static void main(String[] args) {
        log.info("{}", 1 << 3);
    }

    private void regexMatch() {
        String content = "Let's go to a movie.";
        String[] ret = content.split("\\W+");
        log.info("{}", Arrays.toString(ret));
    }

    /**
     * decimal小数位数自定义
     */
    private void decimalScale() {
        BigDecimal decimal = new BigDecimal("100.6664");
        // scale为小数保留位数,RoundingMode为多余小数舍去规则
        BigDecimal result = decimal.setScale(3, RoundingMode.UP);
        log.info("{}", result);
        BigDecimal flag = new BigDecimal(String.valueOf(NUM_FLAG));
        log.info("{}", flag);
    }
    /**
     * 迭代删除
     */
    private void updateIterator() {
        List<String> contents = new ArrayList<>();
        contents.add("a");
        contents.add("b");
        contents.add("c");
        contents.add("d");
        Iterator<String> iterator = contents.iterator();
        while (iterator.hasNext()) {
            contents.remove(iterator.next());
        }
        log.info("complete");
    }

    private void toIntPms() {
        long startTime = System.currentTimeMillis();
        double a, b;
        for (int i = 0; i < 10000; i++) {
            a = Math.random();
            b = Math.random();
            int result = (int) Math.floor(a + b);
            log.info("a: {}, b: {}, result: {}", a, b, result);
        }
        log.info("time consume: {} ms", System.currentTimeMillis() - startTime);

        int result = (int) Math.floor(1.23455);
        log.info("result: {}", result);

        List<String> content = Arrays.asList("3", "2", "1");
        Collections.sort(content);
        log.info("{}", JSON.toJSONString(content));
    }

    /**
     * 优先级队列
     */
    private void priorityQueue() throws NoSuchAlgorithmException {
        val data = new ArrayList<String>();
        data.add("one");
        data.add("two");
        log.info("{}", JSON.toJSONString(data));
        data.clear();
        Random random = SecureRandom.getInstanceStrong();
        double v = random.nextDouble() * 400;
        log.info("{}", v);
        v = random.nextDouble() * 400;
        log.info("{}", v);
        v = random.nextDouble() * 400;
        log.info("{}", v);

    }

    public static String targetField(String source, char split) {
        int bounds = 5;
        int index = 0;
        int start = 0, end = 0;
        while (end < source.length()) {
            if (source.charAt(end) == split) {
                if (++index == bounds) {
                    break;
                }
                start = end;
            }
            end++;
        }
        return source.substring(start + 2, end).trim();
    }

    @Data
    static class Compare implements Comparable<Compare> {
        private int num;

        @Override
        public int compareTo(Compare o) {
            if (num > o.getNum()) {
                return 1;
            } else if (num == o.getNum()) {
                return 0;
            }
            return 1;
        }
    }
}

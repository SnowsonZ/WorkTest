package com.test.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
            Integer result = Double.valueOf(Math.floor(a + b)).intValue();
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
        List<Compare> container = new ArrayList<>();
        Compare compare = new Compare();
        compare.setNum(1);
        container.add(compare);

        Compare compare1 = new Compare();
        compare1.setNum(4);
        container.add(compare1);

        Compare compare2 = new Compare();
        compare2.setNum(2);
        container.add(compare2);

        Compare compare3 = new Compare();
        compare3.setNum(3);
        container.add(compare3);
        Collections.sort(container);
        log.info("{}", container);

        PriorityQueue<Compare> compares = new PriorityQueue<>();
        compares.offer(compare);
        compares.offer(compare1);
        compares.offer(compare2);
        compares.offer(compare3);
        while (!compares.isEmpty()) {
            Compare item = compares.poll();
            log.info("num: {}", item.getNum());
        }

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

    private void serialization() {
        List<One> data = Arrays.asList(new One("1", 1.2d), new One("2", 1.2d));
        String str = JSON.toJSONString(data);
        List<Tow> result = JSON.parseArray(str, Tow.class);
        log.info("{}", result.toString());
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

    @Data
    @AllArgsConstructor
    static class One {
        private String t;
        private double v;
    }

    @Data
    static class Tow {
        @JSONField(name = "t")
        private String time;
        @JSONField(name = "v")
        private double value;
    }
}

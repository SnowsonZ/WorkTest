package com.test.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/29 15:52
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
//        ArrayList<String> contents = new ArrayList<>();
//        contents.add("a");
//        contents.add("b");
//        contents.add("c");
//        contents.add("d");
//        for (String item : contents) {
//            contents.remove(item);
//        }
//        log.info("complete");
        /**
         * 浮点数计算精度问题
         */
//        float a = 1000.000000001f;
//        float b = 100.000002f;
//        System.out.println(a + b);

        /**
         * 取整效率
          */
//        long startTime = System.currentTimeMillis();
//        double a, b;
//        for (int i = 0; i < 10000; i++) {
//            a = Math.random();
//            b = Math.random();
//            Integer result = Double.valueOf(Math.floor(a + b)).intValue();
////            log.info("a: {}, b: {}, result: {}", a, b, result);
//        }
//        log.info("time consume: {} ms", System.currentTimeMillis() - startTime);

//        int result = (int) Math.floor(1.23455);
//        log.info("result: {}", result);

//        List<String> content = Arrays.asList("3", "2", "1");
//        Collections.sort(content);
//        System.out.println(content);

        char split = '\u001E';
        String dns = "Apr 23 20:00:39 RIIL æµéåææ¢é\u001E;F309002000004932B7204E7E\u001E;ipv4\u001E;4\u001E; dns_transaction\u001E;172.17.161.57\u001E;00:1a:a9:7d:ed:00\u001E;172.17.161.57\u001E;172.17.169.2\u001E;49889\u001E;53\u001E;www.zhangyang.liebao\u001E;172.17.169.2\u001E;86400\u001E;5\u001E;1556020822295\u001E;8001";
        String http = "<2>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; http_transaction\u001E;172.17.163.184\u001E;00:1a:a9:7d:ed:76\u001E;172.17.163.184\u001E;172.17.161.31\u001E;54747\u001E;12306\u001E;liebao\u001E;172.17.161.31:12306\u001E;POST\u001E;http://172.17.161.31:12306/v1/event/get_summary/12\u001E;400\u001E;\u001E;\u001E;2018-11-09 14:21:39.181\u001E;2018-11-09 14:21:39.182\u001E;1\u001E;0\u001E;8\u001E;0\u001E;1\u001E;548\u001E;1\u001E;28\u001E;Chrome69.0.3497.92\u001E;Windows";
        String tcp = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;5\u001E;0\u001E;0\u001E;172.17.163.236\u001E;00:1a:a9:7d:ed:76\u001E;00:1a:a9:3f:9f:14\u001E;172.17.163.236\u001E;47.58.191.184\u001E;56002\u001E;445\u001E;6\u001E;3391\u001E;592\u001E;0\u001E;1\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;1\u001E;0\u001E;0\u001E;8.20\u001E;1.94\u001E;2.00\u001E;588.00\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;37\u001E;0\u001E;139\u001E;0\u001E;0\u001E;1\u001E;0\u001E;1541744478895\u001E;1541744478895\u001E;1541744498480\u001E;0\u001E;1\u001E;0\u001E;0\u001E;tcp_session";
        String traffic = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; traffic_session\u001E;1943194\u001E;172.17.160.52\u001E;172.17.160.52\u001E;172.16.2.26\u001E;56568\u001E;53\u001E;\u001E;liebao\u001E;其他类\u001E;3400\u001E;0\u001E;5\u001E;0\u001E;\u001E;\u001E;123456789\u001E;123456789\u001E;0\u001E;1541744480973\u001E;1541744480973\u001E;1541744498610\u001E;1\u001E;17\u001E;0\u001E;\u001E;DEFAULT";
        String tcp_src = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;5\u001E; tcp_session\u001E;0\u001E;0\u001E;172.17.163.236\u001E;00:1a:a9:7d:ed:76\u001E;00:1a:a9:3f:9f:14\u001E;172.17.163.236\u001E;47.58.191.184\u001E;56002\u001E;445\u001E;6\u001E;3391\u001E;592\u001E;0\u001E;1\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;1\u001E;0\u001E;0\u001E;8.20\u001E;1.94\u001E;2.00\u001E;588.00\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;37\u001E;0\u001E;139\u001E;0\u001E;0\u001E;1\u001E;0\u001E;1541744478895\u001E;1541744478895\u001E;1541744498480\u001E;0\u001E;1\u001E;0\u001E;0";
        //        String s = targetField(traffic, split);
        int loop = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            targetField(tcp, split);
//            tcp.substring(60, 72);
        }
        log.info("time consume with costume func: {}", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            tcp.contains("tcp_session");
        }
        log.info("time consume with contains func: {}", System.currentTimeMillis() - startTime);
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
}

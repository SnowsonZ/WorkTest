package com.test.test.util;

import com.google.common.base.Splitter;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/11/19 22:37
 * @Description:
 */
@Slf4j
public class NpvFlowTest {

    private static final String REGEX = "\u001E;";
    private static final String REGEX_REPLACE = "^^;";
    public static void main(String[] args) {
        String pre = "<6>Jun 18 15:28:50 RIIL 流量分析探针^^;F309002000004932B7204E7E^^;ipv4^^;4^^; traffic_session^^;1256549261^^;10.5.24.2^^;10.5.24.2^^;202.119.23.46^^;514^^;10514^^;^^;迅雷^^;P2P软件^^;4201748648^^;0^^;1362900^^;0^^;^^;^^;123456789^^;123456789^^;0^^;1560841137232^^;1560842865230^^;1560842930600^^;2^^;17^^;0^^;^^;DEFAULT";
        String next = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; traffic_session\u001E;1943194\u001E;172.17.160.52\u001E;172.17.160.52\u001E;172.16.2.26\u001E;56568\u001E;53\u001E;\u001E;liebao\u001E;其他类\u001E;3400\u001E;0\u001E;5\u001E;0\u001E;\u001E;\u001E;123456789\u001E;123456789\u001E;0\u001E;1541744480973\u001E;1541744480973\u001E;1541744498610\u001E;1\u001E;17\u001E;0\u001E;\u001E;DEFAULT";
        print(pre, REGEX_REPLACE);
        print(next, REGEX);
    }

    private static void compareContent(String pre, String next, String regex) {
        List<String> preList = Splitter.on(regex).splitToList(pre);
        for (int i = 0; i < preList.size(); i++) {
            System.out.println(i + ": " + preList.get(i));
        }
        System.out.println();
        System.out.println();
        List<String> nextList = Splitter.on(regex).splitToList(next);
        for (int i = 0; i < nextList.size(); i++) {
            System.out.println(i + ": " + nextList.get(i));
        }
    }

    public static void print(String npvFlow, String regex) {
        List<String> flowList = Splitter.on(regex).splitToList(npvFlow);
        for (int i = 0; i < flowList.size(); i++) {
            System.out.println(i + ": " + flowList.get(i));
        }
    }
}
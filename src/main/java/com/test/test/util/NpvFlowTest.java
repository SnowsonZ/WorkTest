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
        String content = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;5\u001E; tcp_session\u001E;0\u001E;0\u001E;172.17.163.236\u001E;00:1a:a9:7d:ed:76\u001E;00:1a:a9:3f:9f:14\u001E;172.17.163.236\u001E;47.58.191.184\u001E;56002\u001E;445\u001E;6\u001E;3391\u001E;592\u001E;0\u001E;1\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;1\u001E;0\u001E;0\u001E;8.20\u001E;1.94\u001E;2.00\u001E;588.00\u001E;0\u001E;0\u001E;0\u001E;10\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;37\u001E;0\u001E;139\u001E;0\u001E;0\u001E;1\u001E;0\u001E;1541744478895\u001E;1541744478895\u001E;1541744498480\u001E;0\u001E;1\u001E;0\u001E;0\u001E;0\u001E;0\u001E;192.168.1.1";
        print(content, REGEX);
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
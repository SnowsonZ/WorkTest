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
    private static final String REGEX_REPLACE = ".;";
    public static void main(String[] args) {
        String pre = "<2>Apr 15 14:23:13 HOST.;F309007000004932B72069DE.;ipv4.;4.; http_transaction.;192.168.2.16.;00:16:cf:41:9c:20.;192.168.2.16.;67.228.110.120.;1580.;80.;1.;www.wireshark.org.;GET.;http://www.wireshark.org/.;200.;text/html.;gzip.;2019-04-15 14:23:14.26.;2019-04-15 14:23:14.26.;0.;0.;1.;0.;1.;469.;3.;3916.; 172.16.4.81;Firefox3.0b5.;Windows";
        String next = "May 24 12:06:22 RIIL.;F309002000004932B7204E7E.;ipv4.;4.; http_transaction.;192.168.252.250.;00:0b:ab:3a:29:fc.;192.168.252.250.;192.168.252.80.;51137.;80.;liebao.;mis.kukahome.com.;GET.;http://mis.kukahome.com/mis.;301.;text/html; charset=UTF-8..;.;2019-05-24 12:06:23.474.;2019-05-24 12:06:23.477.;3.;0.;6.;0.;1.;251.;1.;508.;172.16.4.81.;Chrome62.0.3202.89.;Windows";
        compareContent(pre, next);
    }

    private static void compareContent(String pre, String next) {
        List<String> preList = Splitter.on(REGEX_REPLACE).splitToList(pre);
        for (int i = 0; i < preList.size(); i++) {
            System.out.println(i + ": " + preList.get(i));
        }
        System.out.println();
        System.out.println();
        List<String> nextList = Splitter.on(REGEX_REPLACE).splitToList(next);
        for (int i = 0; i < nextList.size(); i++) {
            System.out.println(i + ": " + nextList.get(i));
        }
    }

    public static void print(String npvFlow) {
        List<String> flowList = Splitter.on(REGEX_REPLACE).splitToList(npvFlow);
        for (int i = 0; i < flowList.size(); i++) {
            System.out.println(i + ": " + flowList.get(i));
        }
    }
}
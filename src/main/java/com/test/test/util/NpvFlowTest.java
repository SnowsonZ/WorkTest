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
public class Test {

    public static final String REGEX = "\u001E;";
    public static void main(String[] args) {
        String content = "Apr 23 20:00:39 RIIL æµéåææ¢é\u001E;F309002000004932B7204E7E\u001E;ipv4\u001E;4\u001E; dns_transaction\u001E;172.17.161.57\u001E;00:1a:a9:7d:ed:00\u001E;172.17.161.57\u001E;172.17.169.2\u001E;49889\u001E;53\u001E;www.zhangyang.liebao\u001E;172.17.169.2\u001E;86400\u001E;5\u001E;1556020822295\u001E;8001";
//        String format = "<6>Jun  2 12:28:39  HOST\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; dns\u001E; user_name\u001E;src_mac\u001E;src_ip\u001E;dst_ip\u001E;src_port\u001E;dst_port\u001E;dst_url\u001E;reply_ip\u001E;ttl\u001E; create_time\u001E; app_delay";

        List<String> result = Splitter.on(REGEX).splitToList(content);
//        List<String> resultFormat = Splitter.on(REGEX).splitToList(format);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
//            System.out.println(i + ": " + result.get(i) + "                   , " + resultFormat.get(i));
        }
        System.out.println();
//        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(content, REGEX);
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println(i + ": " + strings[i]);
//        }
//        System.out.println();
        content = "<7>Nov  9 14:21:39 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; dns\u001E;172.17.163.170\u001E;00:1a:a9:7d:ed:76\u001E;172.17.163.170\u001E;172.16.2.26\u001E;56149\u001E;53\u001E;storage.live.com\u001E;40.90.136.19\u001E;220\u001E;1541744499423\u001E;17";
//        format = "<6>Jun  2 12:28:39  HOST\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; dns_transaction\u001E; user_name\u001E;src_mac\u001E;src_ip\u001E;dst_ip\u001E;src_port\u001E;dst_port\u001E;dst_url\u001E;reply_ip\u001E;ttl\u001E; create_time\u001E; app_delay";
        result = Splitter.on(REGEX).splitToList(content); 
//        resultFormat = Splitter.on(REGEX).splitToList(format);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
        }

//        for (int i = 0; i < resultFormat.size(); i++) {
//            System.out.println(i + ": " + resultFormat.get(i));
//        }

//        Integer num1 = 0, num2 = 0, num3 = 0;
//        System.out.println(0D / 0D);
//        System.out.println(num1.floatValue() / num2.floatValue());
//        NumberZeroTest numberZeroTest = new NumberZeroTest();
//        numberZeroTest.setNum(num1.floatValue() / num2.floatValue());
//        System.out.println(numberZeroTest);
    }
}

//@Data
//@ToString
//class NumberZeroTest{
//    private float num;
//}

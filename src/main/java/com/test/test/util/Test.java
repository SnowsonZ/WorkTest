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
        String content = "";
        List<String> result = Splitter.on(REGEX).splitToList(content);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
        }
        System.out.println();
//        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(content, REGEX);
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println(i + ": " + strings[i]);
//        }
//        System.out.println();
        content = "<6>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;5\u001E; tcp_session\u001E;0\u001E;0\u001E;172.17.163.236\u001E;00:1a:a9:7d:ed:76\u001E;00:1a:a9:3f:9f:14\u001E;172.17.163.236\u001E;47.58.191.184\u001E;56002\u001E;445\u001E;6\u001E;3391\u001E;592\u001E;0\u001E;1\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;1\u001E;0\u001E;0\u001E;8.20\u001E;1.94\u001E;2.00\u001E;588.00\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;0\u001E;37\u001E;0\u001E;139\u001E;0\u001E;0\u001E;1\u001E;0\u001E;1541744478895\u001E;1541744478895\u001E;1541744498480\u001E;0\u001E;1\u001E;0\u001E;0";
        result = Splitter.on(REGEX).splitToList(content);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
        }

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

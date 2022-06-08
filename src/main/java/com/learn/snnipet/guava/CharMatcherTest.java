package com.learn.snnipet.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/19 23:51
 * @Description:
 */
@Slf4j
public class CharMatcherTest {
    public static void main(String[] args) {
        String content = "172.16.3.253,\n\n\n\n\n\n\t\t\t\t      172.17.161.7\n\n\n\n\n\n\t\t                     ,\n172.17.161.177 \n\n\n\n\n\n\t\t         ,\n172.            17.161.114\n\n\n\n\n\n\t\t";

//        CharMatcher matcher = CharMatcher.is('\n');
//        String result = matcher.replaceFrom(content, "");
//        log.info("result: {}", result);

//        log.info("secondStart: {}", secondStart);
//        List<String> resultList2 = Splitter.on(',').splitToList(content.replace("\n", "").replace("\t", "").replace(" ", ""));
//        long endTime = System.currentTimeMillis();
//        log.info("endTime: {}", endTime);
//        log.info("resultList2: {}, timeConsume: {}", resultList2, endTime - secondStart);
//
//        long thirdStart = System.currentTimeMillis();
//        List<String> resultList3 = Splitter.on(",").splitToList(stringFilter(content));
//        log.info("regex resultList3: {}, timeConsume: {}", resultList3, System.currentTimeMillis() - thirdStart);


        /**
         * 性能测试   Guava is winner
         */
        List<String> resultList = null, resultList2 = null, resultList3 = null;
        List<String> result = Splitter.on(',').splitToList(CharMatcher.anyOf("\f\n\r\t' '").replaceFrom(content, ""));
        log.info("result: {}", result);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i ++) {
            //winner
            String result1 = CharMatcher.anyOf("\f\n\r\t").replaceFrom(content, "");
            resultList = Splitter.on(',').splitToList(result1);
        }
        log.info("resultList: {}, timeConsume: {}", resultList, System.currentTimeMillis() - startTime);
        long secondStart = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i ++) {
            resultList2 = Splitter.on(",").splitToList(stringFilter(content));
        }
        log.info("resultList2: {}, timeConsume: {}", resultList2, System.currentTimeMillis() - secondStart);

        long thirdStart = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i ++) {
            resultList3 = Splitter.on(',').splitToList(content.replace("\n", "").replace("\t", "").replace(" ", ""));
        }
        log.info("resultList3: {}, timeConsume: {}",resultList3, System.currentTimeMillis() - thirdStart);
    }

    public static String stringFilter (String str){
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}

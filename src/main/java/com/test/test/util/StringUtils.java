package com.test.test.util;

import com.google.common.base.Splitter;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/4 16:19
 * @Description:
 */
@Slf4j
public class StringUtils {
    public static void main(String[] args) {

        //list contains vs string contains
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("a,");
        }
        builder.append("b");
        String result = builder.toString();
        long startTime = System.currentTimeMillis();
        for (int i = 0 ;i < 100000000; i++) {
            result.contains("b");
        }
        log.info("String timeConsume: {}", System.currentTimeMillis() - startTime);
        List<String> listStr = Splitter.on(",").splitToList(result);
        long secondTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            listStr.contains("b");
        }
        log.info("List timeConsume: {}", System.currentTimeMillis() - secondTime);
    }
}

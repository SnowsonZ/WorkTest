package com.test.test.regex;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/31 15:55
 */
@Slf4j
public class RegexTest {
    public static void main(String[] args) {
        RegexTest instance = new RegexTest();
        instance.test();
    }

    /**
     * 通配符
     */
    private void wildcard() {
        String content = "_&_";
        String result = content.replace("_", "\\_");
        log.info("result: {}", result);
    }

    /**
     * \f -> 匹配一个换页
     * \n -> 匹配一个换行符
     * \r -> 匹配一个回车符
     * \t -> 匹配一个制表符
     * \v -> 匹配一个垂直制表符
     *
     * \s+ 匹配以上任意一个
     */
    private void test() {
        String regex = "\\s+";
        String content = "Hello World...";
        String[] result = content.split(regex);
        log.info("result: {}", JSON.toJSONString(result));
    }
}

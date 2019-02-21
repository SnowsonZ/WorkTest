package com.test.test.regex;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/31 15:55
 */
@Slf4j
public class RegexTest {
    public static void main(String[] args) {
        String content = "_&_";
        String result = content.replace("_", "\\_");
        log.info("result: {}", result);
    }
}

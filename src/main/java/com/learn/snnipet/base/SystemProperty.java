package com.learn.snnipet.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/2/1 16:44
 */
@Slf4j
public class SystemProperty {
    public static void main(String[] args) {
        System.setProperty("line.separator", "");
        String lineSeparator = System.getProperty("line.separator");
        log.info("main, system.line.separator: {}", lineSeparator);
    }
}

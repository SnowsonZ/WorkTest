package com.test.test.base;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/9 15:19
 * @Description:
 */
@Data
//@Component
@Slf4j
public class Holiday implements ApplicationRunner {
    public static void main(String[] args) {
        System.out.println("main: holiday ing...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("un: holiday ing...");
        log.error("un: holiday ing...");
        System.out.println("run: holiday ing...");
    }
}

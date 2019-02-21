package com.test.test.Timer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/1 13:13
 * @Description:
 */
@Slf4j
//@Component
public class TimerTest implements ApplicationRunner {

    @Scheduled(cron = "15 * * * * ? ")
    public void timerTest() {
        log.info("executed...");
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("TimerTest Runner start....");
    }
}

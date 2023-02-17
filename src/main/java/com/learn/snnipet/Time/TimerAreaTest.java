package com.learn.snnipet.Time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/3 10:39
 * @Description:
 */
@Slf4j
public class TimerAreaTest {
    public static void main(String[] args) {
//        LocalDateTime date = LocalDateTime.now();
//        int hour = date.getHour();
//        System.out.println("hour: " + hour);
//        System.out.println("date: " + date);

        LocalDate localDate = LocalDate.ofEpochDay(100);
        log.info("time: {}", localDate);
    }
}

package com.learn.snnipet.base.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/6/11 19:00
 */
@Slf4j
public class TimeTest {
    public static void main(String[] args) {
//        LocalDateTime start = LocalDateTime.now().minusDays(1);
//        log.info("start: {}", start);
//        LocalDateTime end = start.minusDays(29);
//        log.info("end: {}", end);
//
//        int zoneOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
//        log.info("zoneOffset: {}", zoneOffset);
//
//        long hours = Time.hours(1).toMilliseconds();
//        log.info("hours: {}", hours);

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        log.info("now  T: {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
        LocalDateTime startT = now.minusDays(30);
        log.info("startT: {}", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(startT));

    }
}

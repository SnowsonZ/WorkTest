package com.test.test.Time;

import org.apache.flink.api.common.time.Time;

import java.time.LocalDateTime;
import java.util.Calendar;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/27 0:30
 * @Description:
 */
@Slf4j
public class TimeTest {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        log.info("start: {}", start);
        LocalDateTime end = start.minusDays(29);
        log.info("end: {}", end);

        int zoneOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
        log.info("zoneOffset: {}", zoneOffset);

        long hours = Time.hours(1).toMilliseconds();
        log.info("hours: {}", hours);

    }
}

package com.test.test.base;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author Snowson
 * @since 2019/4/19 15:20
 */
public class GenericTest {
    public static void main(String[] args) {
        Long ms = null;
        ceilMs(ms);
    }

    /**
     * Ceil long.
     *
     * @param queryTime the query time 毫秒级时间戳
     * @return long
     */
    public static long ceilMs(long queryTime) {
        long maxMetricTime = LocalDateTime.now().minusSeconds(90 + 60)
                .truncatedTo(ChronoUnit.MINUTES)
                .toEpochSecond(ZoneOffset.ofHours(8));
        if (queryTime <= 0) {
            return maxMetricTime;
        }
        queryTime = queryTime / 60 * 60;
        if(queryTime > maxMetricTime) {
            queryTime = maxMetricTime;
        }

        return queryTime;
    }
}

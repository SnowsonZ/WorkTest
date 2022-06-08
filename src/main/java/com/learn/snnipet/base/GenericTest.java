package com.learn.snnipet.base;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/4/19 15:20
 */
@Slf4j
public class GenericTest {
    static final int HASH_BITS = 0x7fffffff;

    public static void main(String[] args) {
//        int n = 8;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//
//        log.info("{}", Double.valueOf(Math.pow(2, 20)).intValue());
//        final ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>(Double.valueOf(Math.pow(2, 20)).intValue());
//        data.put("first", 1);
//        log.info(dnata.toString());

        System.out.println(spread(10000));

    }

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
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
        if (queryTime > maxMetricTime) {
            queryTime = maxMetricTime;
        }

        return queryTime;
    }
}

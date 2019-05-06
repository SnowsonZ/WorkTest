package com.test.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.test.test.util.ParserUtils.parseFloat;
import static com.test.test.util.ParserUtils.parseInt;
import static com.test.test.util.ParserUtils.parseLong;

/**
 * @author Snowson
 * @since 2019/4/30 9:34
 */
@Slf4j
public class PerformanceTest {
    private static final int loop = 100000;
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String id = "12345678";
        String name = "123";
        String price = "123.45";
        for (int i = 0; i < loop; i++) {
            new Entity(parseInt(name), parseFloat(price), parseLong(id));
        }
        log.info("time consume: {}", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            new Entity(1, 1.2f, 12);
        }
        log.info("time consume: {}", System.currentTimeMillis() - startTime);
    }

    @Data
    @AllArgsConstructor
    static class Entity {
        private int count;
        private float price;
        private long id;


    }
}

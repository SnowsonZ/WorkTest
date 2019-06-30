package com.test.test.number;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/29 17:31
 * @Description:
 */
@Slf4j
public class NumberTransTest {
    public static void main(String[] args) {
        String content = "1234.567F";
        Double aDouble = Double.valueOf(content);
        log.info("result: {}", aDouble);

        Double v = Double.parseDouble("+Infinity");
        log.info("v: {}", v);

        double[] d = {0, Double.NEGATIVE_INFINITY};
        log.info("d: {}", d);
    }
}

package com.test.test.collection;

import com.google.common.collect.ImmutableList;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/11 13:25
 * @Description:
 */
@Slf4j
public class ListTest {
    public static void main(String[] args) {
        ImmutableList<String> list = ImmutableList.of("one", "two", "three");
        String[] array = list.toArray(new String[]{});
        log.info("array: {}, {}, {}", array[0], array[1], array[2]);
    }
}

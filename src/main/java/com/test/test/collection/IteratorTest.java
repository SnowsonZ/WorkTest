package com.test.test.collection;

import com.google.common.collect.ImmutableList;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/23 18:10
 */
@Slf4j
public class IteratorTest {
    public static void main(String[] args) {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        list.forEach(item -> {
            if (item > 3) {
                return;
            }
            System.out.println(item);
        });
    }
}

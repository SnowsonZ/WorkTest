package com.learn.snnipet.collection;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

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

        // foreach 边遍历边删除，不允许同时使用iterator.next和集合自身的元素操作
        ArrayList<String> contents = new ArrayList<>();
        contents.add("a");
        contents.add("b");
        contents.add("c");
        contents.add("d");
        for (String item : contents) {
            contents.remove(item);
        }
        log.info("complete");
    }
}

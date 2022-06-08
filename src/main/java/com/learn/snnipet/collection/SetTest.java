package com.learn.snnipet.collection;

import com.google.common.collect.ImmutableSet;

/**
 * @Author: Snowson
 * @Date: 2019/1/4 15:25
 * @Description:
 */
public class SetTest {
    public static void main(String[] args) {
        ImmutableSet<String> set = ImmutableSet.of();
        System.out.println(set.contains(null));
    }
}

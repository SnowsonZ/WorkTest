package com.learn.utils.tester;

import com.learn.algorithm.leetcode.Xor;
import com.learn.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.learn.utils.OperationUtil.swap;

/**
 * @author Snowson
 **/
@Slf4j
public class XorTester {
    public static void main(String[] args) {
        km(5, 3, 10);
//        log.info("{}", Xor.findOne(DataGenerator.intArray(1)));
//        log.info("{}", Xor.findTwo(DataGenerator.intArray(2)));
    }

    public static void km(int maxType, int count, int range) {
        int numType = (int) (Math.random() * maxType + 5);
        int k = (int) (Math.random() * count + 3);
        int m = (int) (Math.random() * count + 5);
        if (k >= m) {
            m += m - k + 1;
        }
        final int[] array = new int[k + (numType - 1) * m];
        int kv = (int) (Math.random() * range);
        for (int i = 0; i < k; i++) {
            array[i] = kv;
        }
        for (int i = k; i < array.length;) {
            int mv = (int) (Math.random() * range);
            if (mv == kv) {
                mv++;
            }
            for (int j = 0; j < m; j++,i++) {
                array[i] = mv;
            }
        }
        for (int i = 0; i < array.length; i++) {
            swap(array, i, (int) (Math.random() * array.length));
        }
        final int num = Xor.findK(array, k, m);
        log.info("find out the number occurred k times in array, k: {}, num: {}", k, num);
        final Map<Integer, Integer> map = new HashMap<>(numType);
        for (int j : array) {
            map.merge(j, 1, Integer::sum);
        }
        if (map.get(num) == k) {
            System.out.println("success.");
        }else {
            System.out.println("failed.");
        }
    }
}

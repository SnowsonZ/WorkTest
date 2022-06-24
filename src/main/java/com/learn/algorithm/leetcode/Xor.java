package com.learn.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Yi huo.
 *
 * @author Snowson
 */
@Slf4j
public class Xor {

    /**
     * Swap.
     *
     * @param array the array
     * @param i     the
     * @param j     the j
     */
    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    /**
     * 给定array,其中1个数字出现了奇数次，其他所有数字出现偶数次，找出该奇数数字
     *
     * @param array the array
     * @return the int
     */
    public static int findOne(int[] array) {
        if (array == null || array.length <= 0) {
            return -1;
        }
        log.info("{}", array);
        int result = 0;
        for (int ele : array) {
            result ^= ele;
        }
        return result;
    }

    /**
     * 给定array,其中2个数字出现了奇数次，其他所有数字出现偶数次，找出该奇数数字
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] findTwo(int[] array) {
        if (array == null || array.length <= 0) {
            return new int[]{-1, -1};
        }
        log.info("{}", array);
        int xor = 0;
        for (int ele : array) {
            xor ^= ele;
        }
        final int farRight1 = xor & -xor;
        int xorp = 0;
        for (int ele : array) {
            if ((farRight1 & ele) != 0) {
                xorp ^= ele;
            }
        }
        int[] ret = new int[2];
        ret[0] = xor ^ xorp;
        ret[1] = ret[0] ^ xor;
        return ret;
    }

    /**
     * 给定array,其中一个数字出现了K次，其他数字出现了M次，K < M,出现K次的数字
     *
     * @param array the array
     */
    public static int findK(int[] array, int k, int m) {
        if (array == null || array.length <= 0 || k < 1 || k > m) {
            return -1;
        }
        log.info("{}", array);
        final int[] agg = new int[32];
        for (int value : array) {
            for (int j = 0; j < 32; j++) {
                agg[j] += (value >> j) % 2;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += (agg[i] % m > 0 ? 1 : 0) * Math.pow(2, i);
        }
        return result;
    }


    /**
     * Find right 1 int.
     *
     * @param target the target
     * @return the int
     */
    public static int findFarRight1(int target) {
        return target & -target;
    }
}

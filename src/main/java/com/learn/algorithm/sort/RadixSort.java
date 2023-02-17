package com.learn.algorithm.sort;

/**
 * 稳定排序的定义与实现
 * <p>
 * 按位排序/拆分为部分排序
 *
 * @author Snowson
 **/
public class RadixSort {
    /**
     * Sort int [ ].
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] sort(int[] array) {
        radixSort(array, 0, array.length - 1, maxBit(array));
        return array;
    }

    private static void radixSort(int[] array, int left, int right, int maxBit) {
        int bucketSize = 10;
        int[] helper = new int[array.length];
        for (int i = 1; i <= maxBit; i++) {
            int[] bucket = new int[bucketSize];
            for (int j = left; j <= right; j++) {
                int d = getDigital(array[j], i);
                bucket[d]++;
            }
            for (int j = 1; j < bucketSize; j++) {
                bucket[j] = bucket[j] + bucket[j - 1];
            }
            for (int j = right; j >= left; j--) {
                int d = getDigital(array[j], i);
                helper[bucket[d] - 1] = array[j];
                bucket[d]--;
            }
            for (int j = left; j <= right; j++) {
                array[j] = helper[j];
            }
        }
    }

    private static int getDigital(int x, int bit) {
        return (int)(x / Math.pow(10, bit - 1)) % 10;
    }

    public static int maxBit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }
}

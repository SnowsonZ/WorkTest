package com.learn.algorithm.leetcode;

import com.learn.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 对于给定的数组array,针对任意的数组下标，i < j,若有a[i] < a[j],则记录最小值a[i],计算该数组的最小值的和。
 * <p>
 * @author Snowson
 **/
@Slf4j
public class SmallestSum {
    public static int sort(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        return mergeSort(array, 0, array.length - 1);
    }

    private static int merge(int[] array, int start, int mid, int end) {
        int sum = 0;
        final int[] help = new int[array.length];
        for (int i = start; i <= end; i++) {
            help[i] = array[i];
        }
        int m = start, n = mid + 1;
        for (int i = start; i <= end; i++) {
            if (m > mid) {
                array[i] = help[n++];
            } else if (n > end) {
                array[i] = help[m++];
            } else {
                sum += help[m] < help[n] ? (end - n + 1) * array[m] : 0;
                array[i] = help[m] <= help[n] ? help[m++] : help[n++];
            }
        }
        return sum;
    }

    private static int mergeSort(int[] array, int start, int end) {
        if (start >= end) return 0;
        int mid = start + ((end - start) >> 1);
        return mergeSort(array, start, mid) + mergeSort(array, mid + 1, end) + merge(array, start, mid, end);
    }

    public static void main(String[] args) {
        final int[] array = DataGenerator.intArray(50, 10);
        log.info("{}", array);
        final int sum = sort(array);
        log.info("sum: {}", sum);
        log.info("array: {}", array);
    }
}

package com.learn.algorithm.leetcode;

import com.learn.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 逆序对问题
 * <p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/">leetcode</a>
 * @author Snowson
 **/
@Slf4j
public class ReversePair {
    public static int sort(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        return mergeSort(array, 0, array.length - 1);
    }

    private static int merge(int[] array, int start, int mid, int end) {
        int count = 0;
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
                count += help[m] > help[n] ? mid - m + 1 : 0;
                array[i] = help[m] <= help[n] ? help[m++] : help[n++];
            }
        }
        return count;
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

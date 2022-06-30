package com.learn.algorithm.leetcode;

import com.learn.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 给定数组，对于任意的数组下标i,j, 若i < j，且array[i] > 2 * array[j]，记作1个枢纽，计算数组中枢纽的个数。
 * <p>
 * @author Snowson
 **/
@Slf4j
public class biggerThan2Times {
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
        for (int i = start; i <= mid; i++) {
            int indexRight = mid + 1;
            while (indexRight <= end && help[i] > help[indexRight++] * 2) {
                count++;
            }
        }
        int m = start, n = mid + 1;
        for (int i = start; i <= end; i++) {
            if (m > mid) {
                array[i] = help[n++];
            } else if (n > end) {
                array[i] = help[m++];
            } else {
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
        final int[] array = DataGenerator.intArray(10, 10);
        log.info("{}", array);
        final int sum = sort(array);
        log.info("sum: {}", sum);
        log.info("array: {}", array);
    }
}

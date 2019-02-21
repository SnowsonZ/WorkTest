package com.test.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序
 * <p>
 * 时间复杂度： O(nlogn), 稳定
 * <p>
 * 空间负责度：需要额外的存储空间
 * <p>
 * 关键字：分治法
 * <p>
 * 两种方式：自上而下递归；自下而上迭代
 *
 * @author Snowson
 * @since 2019 /2/21 23:27
 */
@Slf4j
public class MergeSort {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] array = {4, 10, 2, 19, 3, 9, 1, 0, 5};
        int length = array.length;
        int[] result = new int[length];
        mergeRecursive(array, result, 0, length - 1);
        log.info("result: {}", result);

        merge(array);
        log.info("result: {}", array);
    }


    /**
     * Merge recursive.
     *
     * @param array  the array
     * @param result the result
     * @param start  the start
     * @param end    the end
     */
    public static void mergeRecursive(int[] array, int[] result, int start,
                                      int end) {
        if (start >= end) {
            return;
        }
        int length = end - start, mid = start + (length >> 1);
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeRecursive(array, result, start1, end1);
        mergeRecursive(array, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = array[start1] <= array[start2] ? array[start1++] : array[start2++];
        }
        while (start1 <= end1) {
            result[k++] = array[start1++];
        }
        while (start2 <= end2) {
            result[k++] = array[start2++];
        }
        for (k = start; k <= end; k++) {
            array[k] = result[k];
        }
    }

    /**
     * Merge.
     *
     * @param array the array
     */
    public static void merge(int[] array) {
        int[] orderedArr = new int[array.length];
        for (int i = 2; i < array.length * 2; i *= 2) {
            for (int j = 0; j < (array.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = left + i / 2 >= array.length ? (array.length - 1) : (left + i / 2);
                int right = i * (j + 1) - 1 >= array.length ? (array.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (array[l] < array[m]) {
                        orderedArr[start++] = array[l++];
                    } else {
                        orderedArr[start++] = array[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = array[l++];
                while (m <= right)
                    orderedArr[start++] = array[m++];
                System.arraycopy(orderedArr, left, array, left, right - left + 1);
            }
        }
    }
}

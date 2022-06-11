package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * 快速排序
 * <p>
 * 时间复杂度： O(nlogn)
 * <p>
 * 关键字：分治法
 * 原理：1. 数组首元素作为界限值x，通过循环，将x置于目标位置，将原数组一分为二。该目标位置满足:位置左边的数组不大于x，位置右侧的数组不小于x.
 *      2. 递归
 * <p>
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/b12fc88b3e00ae442d0d07f36eae73ee.gif" >图解</>
 *
 * @author Snowson
 * @since 2019/2/22 23:45
 */
@Slf4j
public class QuickSort {

    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }
    private static void quickSort(int[] array, int low, int high) {
        int l = low;
        int h = high;
        int key = array[low];

        while (l < h) {
            while (l < h && array[h] >= key)
                h--;
            if (l < h) {
                swap(array, h, l);
                l++;
            }

            while (l < h && array[l] <= key)
                l++;

            if (l < h) {
                swap(array, h, l);
                h--;
            }
        }
        if (l > low) quickSort(array, low, l - 1);
        if (h < high) quickSort(array, l + 1, high);
    }
}

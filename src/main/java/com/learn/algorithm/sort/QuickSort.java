package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import static com.learn.utils.OperationUtil.swap;

/**
 * 快速排序
 * <p>
 * 时间复杂度： 平均：O(nlogn)；最坏：O(N^2)
 * <p>
 * 关键字：分治法
 * 原理：1. 数组首元素作为界限值x，通过循环，将x置于目标位置，将原数组一分为二。该目标位置满足:位置左边的数组不大于x，位置右侧的数组不小于x.<br/>
 *      2. 递归 <br/>
 * <p>
 * 递推公式：sort(array,start, end) = sort(array, start, k - 1) + sort(array, k + 1, end) <br/>
 * <p>
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/b12fc88b3e00ae442d0d07f36eae73ee.gif" >图解</a>
 *
 * @author Snowson
 * @since 2019/2/22 23:45
 */
@Slf4j
public class QuickSort {

    public static int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }
    private static void quickSort(int[] array, int low, int high) {
        if (low >= high) return;
        final int key = partition(array, low, high);
        quickSort(array, low, key - 1);
        quickSort(array, key + 1, high);
    }

    private static int partition(int[] array, int l, int h) {
        int key = array[l];
        while (l < h) {
            while (l < h && array[h] >= key) h--;
            if (l < h) {
                swap(array, h, l);
                l++;
            }

            while (l < h && array[l] <= key) l++;
            if (l < h) {
                swap(array, h, l);
                h--;
            }
        }
        return l;
    }
}

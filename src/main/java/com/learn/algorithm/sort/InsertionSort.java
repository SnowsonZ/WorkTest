package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 插入排序
 * <p>
 * 时间复杂度：最好情况：O(1); 最差情况：O(n^2); 不稳定
 * <p>
 * 空间复杂度：O(1)
 * <p>\
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/772ce55ba647e2db864f849b1c36c570.gif">图解</a>
 *
 * @author Snowson
 * @since 2019 /2/24 23:49
 */
@Slf4j
public class InsertionSort {
    /**
     * 普通插入排序--内层循环逆序时交换元素(类似于冒泡)
     *
     * @param array the array
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
        return array;
    }

    /**
     * 普通插入排序--只在最后找到插入位置后交换一次
     *
     * @param array the array
     */
    public static int[] insertionSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) continue;
            int target = array[i];
            int j = i;
            for (; j > 0 && target < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = target;
        }
        return array;
    }

    /**
     * 二分法插入排序
     * O(nlogn),在插入元素过程中采用二分查找
     *
     * @param array the array
     */
    public static int[] binaryInsetSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) continue;
            if (array[i] <= array[0]) {
                move(0, array, i, array[i]);
                continue;
            }
            binarySearch(0, i - 1, array, array[i], i);
        }
        return array;
    }

    private static void binarySearch(int start, int end, int[] array, int target, int index) {
        if (start + 1 == end) {
            move(end, array, index, target);
            return;
        }
        int mid = (start + end) >> 1;
        if (target > array[mid]) {
            start = mid;
        } else if (target < array[mid]) {
            end = mid;
        } else {
            move(mid + 1, array, index, target);
            return;
        }
        binarySearch(start, end, array, target, index);
    }

    private static void move(int end, int[] array, int index, int target) {
        for (int i = index; i > end; i--) {
            array[i] = array[i - 1];
        }
        array[end] = target;
    }

    private static void swap(int[] array, int i, int j) {
        int v = array[i];
        array[i] = array[j];
        array[j] = v;
    }
}

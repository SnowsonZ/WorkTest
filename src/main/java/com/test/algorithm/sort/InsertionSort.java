package com.test.algorithm.sort;

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
 * @since 2019/2/24 23:49
 */
@Slf4j
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {2, 1, 0, 0, 5, 6, 9, 5, 8};
        insertionSort(array);
        log.info("result: {}", array);
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // TODO 二分法插入排序
}

package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import static com.learn.utils.OperationUtil.swap;

/**
 * 冒泡排序
 * <p>
 * 时间复杂度：不稳定; 最坏：O(n^2);最好：O(n);平均：O(n^2)
 * <p>
 * 空间复杂度：稳定： O(1)
 * <p>
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/b6acad2b1826e280cd04ead18eca3fb4.gif">图解</a>
 *
 * @author Snowson
 * @since 2019/2/22 23:25
 */
@Slf4j
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //-1为了防止溢出
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}

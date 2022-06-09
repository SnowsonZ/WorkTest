package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序
 * <p>
 * 时间复杂度：O(nlog^2n);不稳定
 * <p>
 * 空间复杂度：
 * <p>
 * 关键词：递减增量排序算法, 插入排序的高效改进版;
 * 提升每次移动的步数,(步长选择是关键) 插入及冒泡每次移动一步;
 * 小数据量时效率高，数据量大时效率低于快速排序
 * <p>
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/e0a2dfa6a4a7427983ce70667b0dbdf6.gif">图解</a>
 *
 * @author Snowson
 * @since 2019/2/25 0:08
 */
@Slf4j
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {2, 1, 0, 0, 5, 6, 9, 5, 8};
        shellSort(array);
        log.info("result: {}", array);
    }

    public static void shellSort(int[] array) {
        int number = array.length / 2;
        int i;
        int j;
        int temp;
        while (number >= 1) {
            for (i = number; i < array.length; i++) {
                temp = array[i];
                j = i - number;
                //需要注意的是，这里array[j] < temp将会使数组从大到小排序。
                while (j >= 0 && array[j] > temp) {
                    array[j + number] = array[j];
                    j = j - number;
                }
                array[j + number] = temp;
            }
            number = number / 2;
        }
    }
}

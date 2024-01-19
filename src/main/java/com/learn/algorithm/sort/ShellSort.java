package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序
 * <p>
 * 时间复杂度：平均：O(nlog^2n);最优：O(n);平均：不去定。不稳定
 * <p>
 * 空间复杂度：
 * <p>
 * 关键词：分组排序法
 * <p>
 * 递减增量排序算法(多次分组，组内插入排序。分组步长一次递减，直到步长为1)
 * 插入排序的高效改进版;
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
    public static int[] sort(int[] array) {
        int span = array.length >> 1;
        int i, j;
        int temp;
        while (span >= 1) {
            for (i = span; i < array.length; i++) {
                temp = array[i];
                j = i - span;
                //需要注意的是，这里array[j] < temp将会使数组从大到小排序。
                while (j >= 0 && array[j] > temp) {
                    array[j + span] = array[j];
                    j = j - span;
                }
                array[j + span] = temp;
            }
//            log.info("1 turn: {}", array);
            span >>=  1;
        }
        return array;
    }
}

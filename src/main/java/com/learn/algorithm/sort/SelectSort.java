package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序
 * <p>
 * 时间复杂度：稳定；O(n^2)
 * <p>
 * 空间复杂度：0
 * <p>
 * <a href="https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181120151222.gif">图解</a>
 *
 * @author Snowson
 * @since 2019/2/22 23:40
 */
@Slf4j
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {2, 1, 0, 0, 5, 6, 9, 5, 8};
        selectSort(array);
        log.info("result: {}", array);
    }

    public static void selectSort(int[] a) {
        int minIndex = 0;
        int temp = 0;
        if ((a == null) || (a.length == 0))
            return;
        for (int i = 0; i < a.length; i++) {
            //无序区的最小数据数组下标
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                //在无序区中找到最小数据并保存其数组下标
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //将最小元素放到本次循环的前端
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }
}

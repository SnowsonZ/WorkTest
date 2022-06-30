package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序<br/>
 *
 * <p>
 * 时间复杂度： O(nlogn), 稳定
 * <p>
 * 空间复杂度：O(n)
 * <p>
 * 关键字：分治法
 * <p>
 * 两种方式：自上而下递归；自下而上迭代
 * <p>
 * <p>
 * 递推公式：sort(array, start, end) = merge(sort(array, start, mid),sort(array, mid + 1, end))<br/>
 * <a href="https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181120110141.gif">图解</a>
 *
 * @author Snowson
 * @since 2019 /2/21 23:27
 */
@Slf4j
public class MergeSort {
    /**
     * 自顶向下
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] sortRec(int[] array) {
        int[] aux = new int[array.length];
        sort(array, aux, 0, array.length - 1);
        return array;
    }

    private static void sort(int[] array, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + ((hi - lo) >> 1);
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    /**
     * 自底向上
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] sort(int[] array) {
        int[] aux = new int[array.length];
        for (int i = 1; i < array.length; i = 2 * i) {
            for (int lo = 0; lo < array.length - i; lo += 2 * i) {
                merge(array, aux, lo, lo + i - 1, Math.min(lo + i * 2 - 1, array.length - 1));
            }
        }
        return array;
    }

    private static void merge(int[] array, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(array, lo, aux, lo, hi + 1 - lo);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else array[k] = aux[i] <= aux[j] ? aux[i++] : aux[j++];
        }
    }
}

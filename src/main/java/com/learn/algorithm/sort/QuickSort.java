package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 * <p>
 * 时间复杂度： O(n2)
 * <p>
 * 关键字：分治法
 * <p>
 * <a href="https://diycode.b0.upaiyun.com/photo/2018/b12fc88b3e00ae442d0d07f36eae73ee.gif" >图解</>
 *
 * @author Snowson
 * @since 2019/2/22 23:45
 */
@Slf4j
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {2, 1, 0, 0, 5, 6, 9, 5, 8};
        quickSort(array, 0, array.length - 1);
        log.info("result: {}", array);
    }

    public static void quickSort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
//        log.info("array", arr);
//        System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
        if (l > low) quickSort(arr, low, l - 1);
        if (h < high) quickSort(arr, l + 1, high);
    }
}

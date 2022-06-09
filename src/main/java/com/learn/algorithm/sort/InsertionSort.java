package com.learn.algorithm.sort;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        print();
        testPerformance();
    }

    private static void print() {
        final int[] array = generateArray(10, 10);
        log.info("origin: {}", array);
        insertionSort2(array);
        log.info("result: {}", array);
    }

    private static void testPerformance() {
        final int[] array = generateArray(200000, 1000);
        final int[] a1 = Arrays.copyOf(array, array.length);
        final int[] a2 = Arrays.copyOf(array, array.length);
        final int[] a3 = Arrays.copyOf(array, array.length);
        final Stopwatch watcher = Stopwatch.createStarted();
        insertionSort(a2);
        log.info("time: {}", watcher);
        insertionSort2(a1);
        log.info("time: {}", watcher);
        binaryInsetSort(a3);
        log.info("time: {}", watcher);
    }

    /**
     * 普通插入排序--内层循环逆序时交换元素(类似于冒泡)
     *
     * @param array the array
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

    /**
     * 普通插入排序--只在最后找到插入位置后交换一次
     *
     * @param array the array
     */
    public static void insertionSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) continue;
            int target = array[i];
            int j = i;
            for (; j > 0 && target < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = target;
        }
    }

    /**
     * 二分法插入排序
     * O(nlogn),在插入元素过程中采用二分查找
     *
     * @param array the array
     */
    public static void binaryInsetSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) continue;
            if (array[i] <= array[0]) move(0, array, i, array[i]);
            binarySearch(0, i - 1, array, array[i], i);
        }
    }

    private static void binarySearch(int start, int end, int[] array, int target, int index) {
        if (end == 0 || start + 1 == end) {
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

    /**
     * Generate array int.
     *
     * @param length the length
     * @param range  the range
     * @return the int [ ]
     */
    public static int[] generateArray(int length, int range) {
        final Random random = new Random(System.currentTimeMillis());
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(range));
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

package com.learn.algorithm.sort;

import java.util.Arrays;

/**
 * The type Bucket sort.
 * 空间复杂度：平均：O(n + k);最坏：O(N^2);
 * 空间复杂度：O(n * k)
 * @author Snowson
 */
public class BucketSort {
    /**
     * Sort int [ ].
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] sort(int[] array) {
        int[][] bucket = matchBucket(array);
        for (int[] a : bucket) {
            QuickSort.sort(a);
        }
        int[] result = new int[array.length];
        int k = 0;
        for (int[] l1 : bucket) {
            for (int l2 : l1) {
                if (l2 != -1) result[k++] = l2;
            }
        }
        return result;
    }

    private static int[][] matchBucket(int[] array) {
        int bucketSpan = getBucketSpan(array);
        int bucketSize = getBucketSize(array);
        final int[][] result = new int[bucketSize][array.length];
        for (int[] ele : result) {
            Arrays.fill(ele, -1);
        }
        for (int item : array) {
            result[item / bucketSpan][getIndex(result[item / bucketSpan])] = item;
        }
        return result;
    }

    private static int getBucketSpan(int[] array) {
        return 10;
    }

    private static int getBucketSize(int[] array) {
        return 10;
    }

    private static int getIndex(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == -1) {
                return i;
            }
        }
        return 0;
    }
}

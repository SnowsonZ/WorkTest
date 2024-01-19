package com.learn.algorithm.sort;

/**
 * Radix sort is a non-comparative integer sorting algorithm that works by distributing elements into buckets according
 * to their individual digits. It is generally used for sorting integers or fixed-size strings where the key space
 * (number of possible values for each digit) is relatively small.
 *
 * <p>
 * 稳定排序的定义与实现
 * <p>
 * 按位排序/拆分为部分排序
 * <p>
 * 时间复杂度: O(k * n)
 * 空间复杂的: O(k + n)
 *
 * @author Snowson
 **/
public class RadixSort {
    /**
     * the default sort for int array
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int[] sort(int[] array) {
        radixSort(array, maxBit(array));
        return array;
    }

    /**
     * sort for string array
     *
     * @param array the array
     * @return the string [ ]
     */
    public static String[] sortString(String[] array) {
        radixSort(array, maxBit(array));
        return array;
    }

    /**
     * Radix sort.
     *
     * @param array  the array
     * @param maxBit the max bit 最大数字是几位数。后续按各个位大小排序，决定循环次数
     */
    private  static void radixSort(int[] array, int maxBit) {
        int bucketSize = 10;
        int left = 0;
        int right = array.length - 1;
        int[] helper = new int[array.length];
        for (int i = 1; i <= maxBit; i++) {
            int[] bucket = new int[bucketSize];
            // tip 1: 统计某个位上的数字出现的次数 ，eg:[12,22,1,2].则第一次循环bucket[1]=1,bucket[2]=3;第二次循环bucket[0]=2,bucket[1]=1,bucket[2]=1
            for (int j = left; j <= right; j++) {
                int d = getDigital(array[j], i);
                bucket[d]++;
            }
            // tip3: bucket[index] = bucket[index] + bucket[index - 1]
            for (int j = 1; j < bucketSize; j++) {
                bucket[j] = bucket[j] + bucket[j - 1];
            }
            for (int j = right; j >= left; j--) {
                int d = getDigital(array[j], i);
                // tip4: 找到array[j]应在的bucket[index],bucket还无法排出顺序，依次占据桶内剩余空间即可。
                helper[bucket[d] - 1] = array[j];
                bucket[d]--;
            }
            if (right + 1 - left >= 0) {
                System.arraycopy(helper, left, array, left, right + 1 - left);
            }
        }
    }

    private static int getDigital(int x, int bit) {
        return (int)(x / Math.pow(10, bit - 1)) % 10;
    }

    public static int maxBit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(max, j);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    private  static void radixSort(String[] array, int maxBit) {
        int bucketSize = 123;
        int left = 0;
        int right = array.length - 1;
        String[] helper = new String[array.length];
        for (int i = 1; i <= maxBit; i++) {
            int[] bucket = new int[bucketSize];
            // tip 1: 统计某个位上的数字出现的次数 ，eg:[12,22,1,2].则第一次循环bucket[1]=1,bucket[2]=3;第二次循环bucket[0]=2,bucket[1]=1,bucket[2]=1
            for (int j = left; j <= right; j++) {
                int d = getDigital(array[j], i, maxBit);
                bucket[d]++;
            }
            // tip3: bucket[index] = bucket[index] + bucket[index - 1]
            for (int j = 1; j < bucketSize; j++) {
                bucket[j] = bucket[j] + bucket[j - 1];
            }
            for (int j = right; j >= left; j--) {
                int d = getDigital(array[j], i, maxBit);
                // tip4: 找到array[j]应在的bucket[index],bucket还无法排出顺序，依次占据桶内剩余空间即可。
                helper[bucket[d] - 1] = array[j];
                bucket[d]--;
            }
            if (right + 1 - left >= 0) {
                System.arraycopy(helper, left, array, left, right + 1 - left);
            }
        }
    }

    private static int getDigital(String s, int i, int maxBit) {
        // 字符串右侧补位,转化为等长字符串比较
        return maxBit - s.length() >= i ? 0 : s.charAt(maxBit - i);
    }

    private static int maxBit(String[] array) {
        int max = 0;
        for (String item : array) {
            max = Math.max(max, item.length());
        }
        return max;
    }
}

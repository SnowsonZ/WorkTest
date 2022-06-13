package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import static com.learn.utils.OperationUtil.swap;

/**
 * 堆排序
 * <p>
 * 预备知识：1. 堆是具有以下性质的近似完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。 2. 堆积的性质：即子节点的键值或索引总是小于（或者大于）它的父节点
 * <p>
 * 时间复杂度：平均：Ο(nlogn);
 * <p>
 * 空间复杂度：
 * <p></p>
 * 关键字：堆数据结构; 大顶堆：升序排序；小顶堆：降序排序;
 * <a href="https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181125191942.gif">图解</a>
 *
 * @author Snowson
 * @since 2019/2/25 0:37
 */
@Slf4j
public class HeapSort {
    public static int[] sort(int[] array) {
        /*
         *  1. 堆化
         *  beginIndex = 第一个非叶子节点。
         *  从第一个非叶子节点开始，避免过多无效循环
         */
        int len = array.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--)
            maxHeapify(array, i, len);
        /*
         * 2. 堆排序
         * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
         * 然后重新堆化，直至未排序的堆长度为 0。
         */
        for (int i = len; i > 0; i--) {
            swap(array, 0, i);
            maxHeapify(array, 0, i - 1);
        }
        return array;
    }

    /**
     * 堆化
     *
     * @param arr   the arr
     * @param index 需要堆化处理的数据的索引
     * @param len   未排序的堆（数组）的长度
     */
    private static void maxHeapify(int[] arr, int index, int len) {
        // 左子节点索引
        int li = (index << 1) + 1;
        // 右子节点索引
        int ri = li + 1;
        // 子节点值最大索引，默认左子节点。
        int cMax = li;
        // 左子节点索引超出计算范围，直接返回。
        if (li > len) return;
        // 先判断左右子节点，哪个较大。
        if (ri <= len && arr[ri] > arr[li])
            cMax = ri;
        if (arr[cMax] > arr[index]) {
            // 如果父节点被子节点调换，
            swap(arr, cMax, index);
            // 则需要继续判断换下后的父节点是否符合堆的特性。
            maxHeapify(arr, cMax, len);
        }
    }
}

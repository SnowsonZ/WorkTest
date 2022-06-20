package com.learn.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 计数排序
 * <p>
 * 时间复杂度: O(n)
 * <p>
 * 空间复杂度：
 * <p>
 * 关键字: 元素为非负整数;数据范围应远小于数据规模
 * <p>
 * <a href="https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181126190814.gif">图解</a>
 * <p>
 * 步骤：<br/>
 * 1. 花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max <br/>
 * 2. 开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1) <br/>
 * 3. 数组 B 中 index 的元素记录的值是 A 中某元素出现的次数 <br/>
 * 4. 最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数 <br/>
 * <p>
 *
 * @author Snowson
 * @since 2019 /2/25 0:56
 */
@Slf4j
public class CountSort {
    public static int[] sort(int[] array) {
        int max = getMax(array);
        int[] bucket = new int[max + 1];
        for (int ele : array) {
            bucket[ele] += 1;
        }
        int[] result = new int[array.length];
        int current = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++, current++) {
                result[current] = i;
            }
        }
        return result;
    }

    private static int getMax(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }
}

package com.test.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 计数排序
 * <p>
 * 时间复杂度: O(n + k), k为整数的范围
 * <p>
 * 空间复杂度：
 * <p>
 * 关键字: 比基于比较的排序算法O(nlogn); 输入数据有一定范围;
 * <p>
 * <a href="https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181126190814.gif">图解</a>
 * <p>
 * 步骤：
 * 1. 花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
 * 2. 开辟一块新的空间创建新的数组 B，长度为 ( max - min + 1)
 * 3. 数组 B 中 index 的元素记录的值是 A 中某元素出现的次数
 * 4. 最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
 * <p>
 *
 * @author Snowson
 * @since 2019/2/25 0:56
 */
@Slf4j
public class CountSort {
    public static void sort(int[] number, int d) //d表示最大的数有多少位
    {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][] temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[] order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while (m <= d) {
            for (int i = 0; i < number.length; i++) {
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    public static void main(String[] args) {
        int[] data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
        CountSort.sort(data, 3);
        log.info("result: {}", data);
    }
}

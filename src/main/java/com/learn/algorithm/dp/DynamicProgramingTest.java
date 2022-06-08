package com.learn.algorithm.dp;

import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Dynamic programing test.
 *
 * @author Snowson
 * @since 2019 /3/28 17:54
 */
@Slf4j
public class DynamicProgramingTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        int[] items = {10, 9, 4, 3, 5, 1};
//        log.info("max: {}", knapsack2(items, items.length, 2));

//        int[] goods = {601};
//        int[] goods = {100, 160, 321, 132, 333, 102, 321};
//        shop(goods, goods.length, 200);

//        log.info("最短路径：{}", minDist(matrix, n));

        coin(new int[]{100, 50, 20, 10, 5, 1}, 6, 101);

    }

    /**
     * Knapsack 2 int.
     * 使用一维数组优化背包问题
     *
     * @param items the items
     * @param n     the n
     * @param w     the w
     * @return the int
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理,可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; j--) {// 把第 i 个物品放入背包, j必须从大到小
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i]) return i;
        }
        return 0;
    }

    /**
     * Knapsack int.
     * 背包问题--二位数组
     *
     * @param items the items
     * @param n     the n
     * @param w     the w
     * @return the int
     */
    public static int knapsack(int[] items, int n, int w) {
        boolean[][] contents = new boolean[n][w + 1];
        contents[0][0] = true;
        if (items[0] <= w) {
            contents[0][items[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                //不把i放入背包
                if (contents[i - 1][j]) {
                    contents[i][j] = true;
                }
            }

            for (int j = 0; j <= w - items[i]; j++) {
                //把i放入背包
                if (contents[i - 1][j]) {
                    contents[i][j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (contents[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Shop.
     * 购物问题--如何最合算的使用满减优惠
     *
     * @param goods the goods
     * @param n     the n
     * @param w     the w
     */
    public static void shop(int[] goods, int n, int w) {
        boolean[][] conditions = new boolean[n][3 * w + 1];
        conditions[0][0] = true;
        if (goods[0] <= 3 * w) {
            conditions[0][goods[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3 * w + 1; j++) {
                if (conditions[i - 1][j]) {
                    conditions[i][j] = true;
                }
            }
            for (int j = 0; j < 3 * w + 1 - goods[i]; j++) {
                if (conditions[i - 1][j]) {
                    conditions[i][j + goods[i]] = true;
                }
            }
        }

        int target;
        for (target = w; target < 3 * w + 1; target++) {
            if (conditions[n - 1][target]) {
                break;
            }
        }
        if (target >= 3 * w + 1) {
            log.info("没有最合算的花费...");
            return;
        } else {
            log.info("最实惠的褥羊毛花费: {}", target);
        }
        for (int i = n - 1; i >= 0; i--) {
            if (target - goods[i] >= 0 && conditions[n - 1][target - goods[i]]) {
                log.info("购买该商品,价格为：{}", goods[i]);
                target -= goods[i];
            }
        }
    }

    private static int[][] matrix =
            {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private static int n = 4;
    private static int[][] mem = new int[4][4];

    /**
     * Min dist int.
     * 状态转移方程法--递归+备忘录
     *
     * @param i the
     * @param j the j
     * @return the int
     */
    public static int minDist(int i, int j) { // 调用 minDist(n-1, n-1);
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    /**
     * Min dist int.
     * 状态转移表法--计算最短路径
     *
     * @param paths the paths
     * @param n     the n
     * @return int
     */
    public static int minDist(int[][] paths, int n) {
        int[][] result = new int[n][n];
        result[0][0] = paths[0][0];
        for (int i = 1; i < n; i++) {
            result[0][i] = result[0][i - 1] + paths[0][i];
            result[i][0] = result[i - 1][0] + paths[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + paths[i][j];
            }
        }

        return result[n - 1][n - 1];
    }

    public static void coin(int[] coins, int n, int w) {

        int sum = 0;
        int count = 0;
        ArrayList<Integer> plan = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (sum + coins[i] <= w) {
                sum += coins[i];
                plan.add(coins[i]);
                count++;
            }
            if (sum == w) {
                break;
            }
        }

        if (sum == w) {
            log.info("凑够所需金额的硬币个数为：{}, 方案是：{}", count, plan);
        } else {
            log.info("没有找到合适的方案");
        }

    }

}

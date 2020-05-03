package com.test.test.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/12/17 1:18
 */
@Slf4j
public class CollectionTest {
    public static void main(String[] args) {
    }

    /**
     * 指定sort方法为JDK1.6,兼容sort返回值只有两种的编码方式
     */
    private void mergeStateType() {
        String property = System.getProperty("java.util.Arrays.useLegacyMergeSort");
        System.out.println(property);
        ArrayList<Task> data = new ArrayList<>();
        for (double i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                data.add(new Task(Double.NaN));
                continue;
            }
            data.add(new Task(i));
        }
        Collections.sort(data);
        System.out.println(data.toString());
    }

}


class Task implements Comparable<Task> {
    private double priority;

    public double getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Task(double priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        if (priority >= o.getPriority()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.priority);
    }
}

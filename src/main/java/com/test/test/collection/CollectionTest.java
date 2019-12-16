package com.test.test.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Data;

/**
 * @author Snowson
 * @since 2019/12/17 1:18
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<Task> result = Arrays.asList(new Task(10), new Task(9), new Task(6), new Task(9));
        Collections.sort(result);
        System.out.println(result.toString());
    }
}

@Data
class Task implements Comparable<Task> {
    private int priority;

    public Task(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return this.priority + "";
    }
}

package com.learn.utils.tester;

import com.google.common.base.Stopwatch;
import com.learn.algorithm.sort.HeapSort;
import com.learn.algorithm.sort.QuickSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.learn.utils.DataGenerator.intArray;

/**
 * @author Snowson
 **/
@Slf4j
public class SortTester {

    public static void main(String[] args) {
//        _assert(HeapSort::sort, QuickSort::quickSort);
        print(HeapSort::sort);
    }

    public static void _assert(Function<int[], int[]> fun1, Function<int[], int[]> fun2) {
        final int[] array = intArray(100, 50);
        final int[] array1 = Arrays.copyOf(array, array.length);
        Assert.isTrue(Arrays.equals(fun1.apply(array), fun2.apply(array1)), "assert failed.");
        log.info("assert success.");
    }

    public static void print(Function<int[], int[]> func) {
        final int[] array = intArray(10, 50);
        log.info("origin: {}", array);
        func.apply(array);
        log.info("result: {}", array);
    }

    public static void testPerformance(List<Function<int[], int[]>> functions) {
        final int[] array = intArray(200000, 1000);
        final ArrayList<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < functions.size(); i++) {
            arrays.add(Arrays.copyOf(array, array.length));
        }
        final Stopwatch watcher = Stopwatch.createStarted();
        for (int i = 0; i < arrays.size(); i++) {
            functions.get(i).apply(arrays.get(i));
            log.info("time: {}", watcher);
        }
    }
}
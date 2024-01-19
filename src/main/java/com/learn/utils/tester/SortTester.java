package com.learn.utils.tester;

import com.google.common.base.Stopwatch;
import com.learn.algorithm.sort.RadixSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.learn.utils.DataGenerator.*;

/**
 * @author Snowson
 **/
@Slf4j
public class SortTester {

    public static void main(String[] args) {
        print(RadixSort::sortString, stringArray(4, 20));
    }

    public static void _assert(Function<int[], int[]> fun1, Function<int[], int[]> fun2) {
        final int[] array = intArray(100, 100000);
        final int[] array1 = Arrays.copyOf(array, array.length);
        Assert.isTrue(Arrays.equals(fun1.apply(array), fun2.apply(array1)), "assert failed.");
        log.info("assert success.");
    }

    public static <T> void print(Function<T[], T[]> func, T[] array) {
        log.info("origin: {}", Arrays.asList(array));
        final T[] result = func.apply(array);
        log.info("result: {}", Arrays.asList(result));
    }

    public static void testPerformance(List<Function<int[], int[]>> functions) {
        final int[] array = intArray(200000, 100);
        final ArrayList<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < functions.size(); i++) {
            arrays.add(Arrays.copyOf(array, array.length));
        }
        final Stopwatch watcher = Stopwatch.createStarted();
        for (int i = 0; i < arrays.size(); i++) {
            functions.get(i).apply(arrays.get(i));
            log.info("{}: time: {}", functions.get(i).getClass().getSimpleName(), watcher);
        }
    }
}

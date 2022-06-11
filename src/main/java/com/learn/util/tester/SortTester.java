package com.learn.util.tester;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static com.learn.util.DataGenerator.generateArray;
import static com.learn.algorithm.sort.InsertionSort.*;

/**
 * @author Snowson
 **/
@Slf4j
public class SortTester {

    public static void main(String[] args) {
        InsertSortTest.resultAssert();
    }

    public static class InsertSortTest {
        public static void resultAssert() {
            final int[] array = generateArray(100, 50);
            final int[] array1 = Arrays.copyOf(array, array.length);
            log.info("result: {}", Arrays.equals(binaryInsetSort(array), insertionSort2(array1)));
        }

        public static void print() {
            final int[] array = generateArray(100, 50);
            log.info("origin: {}", array);
            binaryInsetSort(array);
            log.info("result: {}", array);
        }

        public static void testPerformance() {
            final int[] array = generateArray(200000, 1000);
            final int[] a1 = Arrays.copyOf(array, array.length);
            final int[] a2 = Arrays.copyOf(array, array.length);
            final int[] a3 = Arrays.copyOf(array, array.length);
            final Stopwatch watcher = Stopwatch.createStarted();
            insertionSort(a2);
            log.info("time: {}", watcher);
            insertionSort2(a1);
            log.info("time: {}", watcher);
            binaryInsetSort(a3);
            log.info("time: {}", watcher);
        }
    }

}

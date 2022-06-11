package com.learn.utils;

/**
 * @author Snowson
 **/
public class OperationUtil {
    private static void swap(int[] array, int i, int j) {
        int v = array[i];
        array[i] = array[j];
        array[j] = v;
    }
}

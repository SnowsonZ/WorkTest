package com.learn.utils;

/**
 * @author Snowson
 **/
public class OperationUtil {
    public static void swap(int[] array, int i, int j) {
        int v = array[i];
        array[i] = array[j];
        array[j] = v;
    }
}

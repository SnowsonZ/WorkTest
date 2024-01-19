package com.learn.utils;

/**
 * @author Snowson
 **/

import java.util.Arrays;

import java.util.Arrays;

public class RadixSort {

    private static void countingSort(String[] arr, int exp) {
        int n = arr.length;
        String[] output = new String[n];
        final int CHARACTER_RANGE = 256; // Assuming ASCII characters

        int[] count = new int[CHARACTER_RANGE];

        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (String s : arr) {
            int index = (s.length() >= exp) ? s.charAt(s.length() - exp) : 0;
            count[index]++;
        }

        // Change count[i] so that count[i] now contains the actual
        // position of this digit in output[]
        for (int i = 1; i < CHARACTER_RANGE; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i].length() >= exp) ? arr[i].charAt(arr[i].length() - exp) : 0;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copy the output array back to arr[]
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void radixSort(String[] arr) {
        int maxLen = Arrays.stream(arr).mapToInt(String::length).max().orElse(0);

        // Pad the strings with spaces to make them equal in length
        for (int i = 0; i < arr.length; i++) {
            while (arr[i].length() < maxLen) {
                arr[i] = " " + arr[i];
            }
        }

        // Sort the array using counting sort for each character position
        for (int exp = maxLen; exp >= 1; exp--) {
            countingSort(arr, exp);
        }
    }

    private static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s.trim() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "grape", "date", "cherry", "kiwi"};
        System.out.println("Original Array:");
        printArray(arr);

        radixSort(arr);

        System.out.println("Sorted Array:");
        printArray(arr);
    }
}




package com.learn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Snowson
 **/
public class DataGenerator {
    /**
     * Generate array int.
     *
     * @param length the length
     * @param range  the range
     * @return the int [ ]
     */
    public static int[] intArray(int length, int range) {
        final Random random = new Random(System.currentTimeMillis());
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(range));
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<String> stringArray(int maxLength, int size) {
        return stringArray(maxLength, size, 26);
    }

    public static List<String> stringArray(int maxLength, int size, int range) {
        final Random random = new Random(System.currentTimeMillis());
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final int length = random.nextInt(maxLength) + 2;
            final char[] chars = new char[length];
            for (int j = 0; j < length; j++) {
                final int radix = random.nextInt(range);
                chars[j] = (char) ('a' + radix);
            }
            result.add(new String(chars));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(stringArray(5, 100));
    }
}

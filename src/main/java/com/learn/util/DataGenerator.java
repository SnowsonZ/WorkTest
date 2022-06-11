package com.learn.util;

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
    public static int[] generateArray(int length, int range) {
        final Random random = new Random(System.currentTimeMillis());
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(range));
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

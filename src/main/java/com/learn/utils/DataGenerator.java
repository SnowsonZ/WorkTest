package com.learn.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Snowson
 **/
@Slf4j
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

    public static int[] intArray(int oddNumCount) {
        if (oddNumCount <= 0) {
            return null;
        }
        final ArrayList<Integer> oddList = new ArrayList<>();
        for (int i = 0; i < oddNumCount; i++) {
            final int oddCount =  2 * (int) (Math.random() * 2) + 1;
            int oddNum = (int) (Math.random() * 10);
            if (oddList.contains(oddNum)) {
                oddNum++;
            }
            for (int j = 0; j < oddCount; j++) {
                oddList.add(oddNum);
            }
        }
        final ArrayList<Integer> list = new ArrayList<>(oddList);
        final int evenNumCount = (int) (Math.random() * 10 + 2);
        for (int i = 0; i < evenNumCount; i++) {
            int count = 2 * (int) (Math.random() + 1);
            int evenNum = (int) (Math.random() * 10);
            if (oddList.contains(evenNum)) {
                evenNum++;
            }
            for (int j = 0; j < count; j++) {
                list.add(evenNum);
            }
        }
        final int[] result = list.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < result.length; i++) {
            OperationUtil.swap(result, i, (int) (Math.random() * result.length));
        }
        return result;
    }

    public static void main(String[] args) {
        log.info("{}", intArray(1));
        log.info("{}", intArray(2));
    }
}

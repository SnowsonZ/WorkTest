package com.test.algorithm.sort;

import java.util.HashSet;

/**
 * @author Snowson
 **/
public class AABTest {
    public int count(String content) {
        if (content == null || content.length() <= 0) {
            return 0;
        }
        final HashSet<String> result = new HashSet<>();
        for (int index = 0; index < content.length(); index++) {
            if (index + 2 >= content.length()) {
                break;
            }
            final String cur = content.substring(index, index + 3);
            if (cur.indexOf(index) == cur.indexOf(index + 1) && cur.indexOf(index) != cur.indexOf(index + 2)) {
                result.add(cur);
            }
        }
        return result.size();
    }

    public static void main(String[] args) {
        String content = "aabbccaab";
        final AABTest driver = new AABTest();
        final long startTime = System.currentTimeMillis();
        final int count = driver.count(content);
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
        System.out.println(count);
    }
}

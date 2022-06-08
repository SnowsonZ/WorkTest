package com.learn.snnipet.util;

/**
 * SplitUtils
 *
 * @author rcao
 * @version 1.0
 * @date 2019/4/24
 **/
public class SplitUtils {

    private SplitUtils() {}
    /**
     * Fast split string [ ].
     *
     * @param count the count
     * @param s     the s
     * @param del   the del
     * @return the string [ ]
     */
    public static String[] fastSplit(int count, String s, char del) {
        String[] array = new String[count];
        int a = -2;
        int b = 0;
        for (int i = 0; i < count; i++) {
            while (b < s.length() && s.charAt(b) != del) {
                b++;
            }
            if(b > s.length()) {
                break;
            }
            array[i] = s.substring(a + 2, b);
            a = b;
            b += 2;
        }
        return array;
    }
}

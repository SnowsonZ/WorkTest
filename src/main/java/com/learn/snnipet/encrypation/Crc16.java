package com.learn.snnipet.encrypation;

/**
 * @author Snowson
 **/
public class Crc16 {
    /**
     * 一个字节包含位的数量 8
     */
    private static final int BITS_OF_BYTE = 8;

    /**
     * 多项式
     */
    private static final int POLYNOMIAL = 0xA001;

    /**
     * 初始值
     */
    private static final int INITIAL_VALUE = 0xFFFF;

    /**
     * CRC16 编码
     *
     * @param bytes 编码内容
     * @return 编码结果
     */
    public static String crc16(int[] bytes) {
        int res = INITIAL_VALUE;
        for (int data : bytes) {
            res = res ^ data;
            for (int i = 0; i < BITS_OF_BYTE; i++) {
                res = (res & 0x0001) == 1 ? (res >> 1) ^ POLYNOMIAL : res >> 1;
            }
        }
        return convertToHexString(revert(res));
    }

    /**
     * 翻转16位的高八位和低八位字节
     *
     * @param src 翻转数字
     * @return 翻转结果
     */
    private static int revert(int src) {
        int lowByte = (src & 0xFF00) >> 8;
        int highByte = (src & 0x00FF) << 8;
        return lowByte | highByte;
    }

    private static String convertToHexString(int src) {
        return Integer.toHexString(src);
    }

    public static void main(String[] args) {

        int[] data = new int[]{0x01, 0x04, 0x04, 0x01, 0x0e, 0x01, 0xde};
        System.out.println(Crc16.crc16(data));


        int b = 0x0201;

        // 将16位的高8位转换为低8位
        int lowByte = (b & 0xFF00) >> 8;
        System.out.println(lowByte);

        // 将16位的低8位转换为高8位
        int highByte = (b & 0x00FF) << 8;
        System.out.println(highByte);

        // 按位或运算，将两个数相加
        int c = lowByte | highByte;
        System.out.println(c);

    }
}

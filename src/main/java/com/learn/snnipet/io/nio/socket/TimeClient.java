package com.learn.snnipet.io.nio.socket;

/**
 * @author Snowson
 * @date 2020/5/3 21:03
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient- 001").start();
    }
}

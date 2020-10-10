package com.test.test.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/10/9 10:05
 */
@Slf4j
public class Test {
    private static volatile Test instance;

    public static Test getInstance() {
        if (instance == null) {
            log.info("{}, enter", Thread.currentThread().getName());
            synchronized (Test.class) {
                if (instance == null) {
                    instance = new Test();
                }
                log.info("sleep after: {}", Thread.currentThread().getName());
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        final ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(new Worker());
        }
        service.shutdown();
    }

}

@Slf4j
class Worker implements Runnable {

    @Override
    public void run() {
        final Test instance = Test.getInstance();
        log.info(instance.toString());
    }
}
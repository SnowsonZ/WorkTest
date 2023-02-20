package com.learn.snnipet.base.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/7/16 16:46
 */
@Slf4j
public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Task1());
        service.submit(new Task1());
        service.shutdown();
    }

    private int count;

    private synchronized void add10K() {
        int idx = 0;
        while (idx++ < 100_000_000) {
            count += 1;
        }
    }

    private synchronized void test() {
        int x = 1;
        int y = 2;
        int ret = x + y;
        log.info("{}", ret);
    }

    private void testSynchronized() {
        new Thread(() -> test()).start();
        new Thread(() -> test()).start();
    }

    private long calc() throws InterruptedException {
        final Thread t1 = new Thread(() -> add10K());
        final Thread t2 = new Thread(() -> add10K());
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return count;
    }
}

@Slf4j
class Task implements Runnable {

    @Override
    public void run() {
        Product instance = Product.getInstance(Thread.currentThread().getName());
        log.info("info: {}", instance.getName());
    }
}

@Data
class Product {
    static volatile Product instance;
    String name;

    private Product(String name) {
        this.name = name;
    }

    static Product getInstance(String name) {
        if (instance == null) {
            synchronized (Product.class) {
                if (instance == null) {
                    instance = new Product(name);
                }
            }
        }

        return instance;
    }
}

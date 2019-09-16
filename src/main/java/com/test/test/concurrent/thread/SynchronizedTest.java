package com.test.test.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/7/16 16:46
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Task());
        service.submit(new Task());
        service.shutdown();
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

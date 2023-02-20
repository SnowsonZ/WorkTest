package com.learn.snnipet.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 活锁
 *
 * @author Snowson
 * @date 2020/10/10 10:18
 */
@Slf4j
public class LockTest {
    public final Lock lock = new ReentrantLock();
    private int value;

    public void doOne(LockTest dst, Integer opt) throws InterruptedException {
        boolean willGo = true;
        while (willGo) {
            if (lock.tryLock(10, NANOSECONDS)) {
                try {
                    log.info("{} got src lock", Thread.currentThread().getName());
                    if (dst.lock.tryLock(5, NANOSECONDS)) {
                        try {
                            log.info("{} got dst lock", Thread.currentThread().getName());
                            value += opt;
                            log.info("{} value: {}", Thread.currentThread().getName(), value);
                            willGo = false;
                        } finally {
                            dst.lock.unlock();
                            log.info("{} dst unlock", Thread.currentThread().getName());
                        }
                    }
                } finally {
                    lock.unlock();
                    log.info("{} src unlock", Thread.currentThread().getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        final LockTest a = new LockTest();
        final LockTest b = new LockTest();
        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Task(() -> {
            try {
                a.doOne(b, 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        service.submit(new Task(() -> {
            try {
                b.doOne(a, 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        service.shutdown();
    }
}

@Slf4j
class Task implements Runnable {
    private Runnable run;

    public Task(Runnable run) {
        this.run = run;
    }

    @Override
    public void run() {
        run.run();
    }
}

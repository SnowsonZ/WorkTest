package com.learn.snnipet.base.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/12/13 16:59
 */
public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> THREAD_ID =
            ThreadLocal.withInitial(nextId::getAndIncrement);

    public static int get() {
        return THREAD_ID.get();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.submit(new Task1());
        }
        service.shutdown();
    }
}

@Slf4j
class Task1 implements Runnable {

    @Override
    public void run() {
        log.info("id: {}", ThreadId.get());
    }
}

package com.learn.snnipet.base.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Snowson
 **/
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        final ScheduledExecutorService sExecutors = Executors.newScheduledThreadPool(1);
        sExecutors.scheduleAtFixedRate(getTask(3), 2, 5, TimeUnit.SECONDS);
        sExecutors.scheduleAtFixedRate(getTask(4), 1, 5, TimeUnit.SECONDS);
        sExecutors.scheduleAtFixedRate(getTask(5), 0, 5, TimeUnit.SECONDS);
//        sExecutors.schedule(() -> log.info("record-1..."), 5, TimeUnit.SECONDS);
//        sExecutors.schedule(() -> log.info("record-2..."), 20, TimeUnit.SECONDS);
    }

    private static Runnable getTask(int sleepTime) {
        return () -> {
            try {
                System.out.println("sleep time: " + sleepTime);
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}

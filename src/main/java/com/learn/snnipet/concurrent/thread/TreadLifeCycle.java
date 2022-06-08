package com.learn.snnipet.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/10/15 11:07
 */
@Slf4j
public class TreadLifeCycle {

    public ThreadCtl getCtl() {
        return new ThreadCtl();
    }

    public static void main(String[] args) {
        final ThreadCtl ctl = new TreadLifeCycle().getCtl();
        final ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(() -> {
            ctl.testLock();
//            ctl.stop();
        });
        service.submit(() -> {
            ctl.start();
//            ctl.stop();
        });
        service.shutdown();
    }

    @Slf4j
    static class ThreadCtl {
        volatile boolean isStop;
        Thread task = null;
        boolean isStarted;

        public synchronized void start() {
//            if (isStarted) {
//                return;
//            }
            isStarted = true;
            task = new Thread(() -> {
                while (!isStop) {
                    try {
                        log.info("{} before sleep", Thread.currentThread().getName());
                        Thread.sleep(1000);
                        log.info("{} after sleep", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            task.start();
        }

        public void testLock() {
            log.info("{}", "start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}", "end");
        }

        public synchronized void stop() {
            isStop = true;
            task.interrupt();
            isStarted = false;
        }
    }
}

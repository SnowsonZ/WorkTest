package com.learn.snnipet.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/9 16:24
 */
@Slf4j
public class SynchronizedTest {
    public static void main(String[] args) {
        PageView pageView = new PageView();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskUpdate(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.execute(new TaskRead(pageView));
        es.shutdown();
        log.info("main info");
    }
}

@Slf4j
class TaskUpdate implements Runnable {
    private boolean cancel;
    private PageView pageView;

    public TaskUpdate(PageView pageView) {
        this.pageView = pageView;
    }

    @Override
    public void run() {
        while (!cancel) {
            if (pageView != null) {
                pageView.getValue(true);
                pageView.updateValueObj();
                pageView.getValue(true);

                if (pageView.getValue(false) > 10) {
                    cancel = true;
                }
            }
        }
    }

    public void cancel() {
        this.cancel = true;
    }
}

@Slf4j
class TaskRead implements Runnable {
    private boolean cancel;
    private PageView pageView;

    public TaskRead(PageView pageView) {
        this.pageView = pageView;
    }

    @Override
    public void run() {
        while (!cancel) {
            if (pageView != null) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pageView.getValue(false);

                if (pageView.getValue(false) > 10) {
                    cancel = true;
                }
            }
        }
    }

    public void cancel() {
        this.cancel = true;
    }
}

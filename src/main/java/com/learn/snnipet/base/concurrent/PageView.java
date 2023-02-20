package com.learn.snnipet.base.concurrent;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/9 16:25
 */
@Slf4j
public class PageView implements Serializable {
    private static final long serialVersionUID = -5516224804661792052L;

    private Integer value = 0;
    // 互斥锁可以为非final, 但是不能用资源对象做为锁对象
    private final Object obj = new Object();

    public synchronized int getValue(boolean show) {
        if(show) {
            log.info("pageView value_1 update: {}", value);
        }else {
//            if(value % 2 == 0) {
                log.info("pageView value_1 read: {}", value);
//            }
        }
        return value;
    }

    public synchronized void updateValue() {
        this.value++;
        log.info("pageView value update: {}", value);
    }

    public void updateValueObj() {
        synchronized (this) {
            this.value++;
            log.info("pageView value updateObj: {}", value);
        }
    }
}

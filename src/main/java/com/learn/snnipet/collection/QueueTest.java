package com.learn.snnipet.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Snowson
 **/
public class QueueTest {
    public static void main(String[] args) {
        final DelayQueue<Delayed> queue = new DelayQueue<>();
        queue.add(new Delayed() {
            @Override
            public int compareTo(Delayed o) {
                return o.compareTo(this);
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return 0;
            }
        });
    }
}

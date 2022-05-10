package com.test.test.encrypation;

import lombok.Data;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Snowson
 **/
public class AppEntry {
    public static void main(String[] args) throws InterruptedException {
        final DelayQueue<MessageDelayed> mds = new DelayQueue<>();
        mds.offer(new MessageDelayed(2));
        mds.offer(new MessageDelayed(3));
        mds.offer(new MessageDelayed(4));
        while (true) {
            final MessageDelayed msg = mds.poll(10, TimeUnit.SECONDS);
            if (msg == null) {
                break;
            }
            System.out.println(msg.getTimeDelay());
        }
    }

    @Data
    private static class MessageDelayed implements Delayed {
        private long timeDelay;

        public MessageDelayed(long timeDelay) {
            this.timeDelay = timeDelay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(timeDelay, TimeUnit.SECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return 1;
        }
    }
}

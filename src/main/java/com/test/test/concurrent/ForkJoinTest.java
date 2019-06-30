package com.test.test.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/6/14 15:16
 */
@Slf4j
public class ForkJoinTest {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(3);
        Fibonacci task = new Fibonacci(20);
        Long result = pool.invoke(task);
        log.info("result: {}", result);
    }

    static class Fibonacci extends RecursiveTask<Long> {

        private static final long serialVersionUID = -240542087768338961L;

        private long num;

        Fibonacci(long num) {
            this.num = num;
        }

        @Override
        protected Long compute() {
            if (num <= 1L) {
                return num;
            }
            Fibonacci t1 = new Fibonacci(num - 1);
            t1.fork();
            Fibonacci t2 = new Fibonacci(num - 2);
            return t2.compute() + t1.join();
        }
    }


}

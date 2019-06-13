package com.test.test.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import lombok.var;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Snowson
 * @since 2019/6/12 10:19
 */
@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFutureTest instance = new CompletableFutureTest();
        instance.combine();
        instance.or();
        instance.then();
    }

    /**
     * AND汇聚关系
     */
    private void combine() {
        var f1 = CompletableFuture.runAsync(() -> {
            log.info("T1: 洗水壶...");
            sleep(1, SECONDS);
            log.info("T1: 烧开水");
            sleep(1, SECONDS);
        });

        var f2 = CompletableFuture.supplyAsync(() -> {
            log.info("T2: 洗茶壶...");
            sleep(1, SECONDS);
            log.info("T2: 洗茶杯...");
            sleep(1, SECONDS);
            log.info("T2: 拿茶叶...");
            sleep(1, SECONDS);
            return "龙井";
        });
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            log.info("T2: 拿到茶叶: {}", tf);
            sleep(1000, MILLISECONDS);
            log.info("T2: 煮茶...");
            sleep(1, SECONDS);
            return "上茶: " + tf;
        });
    }

    /**
     * 异常捕获
     * Exception handle.
     *
     * <p>
     * exceptionally：try catch
     * <p>
     * handle: try finally,带有返回值
     * <p>
     * whenComplete: try finally,无返回值
     *
     * @param future the future
     */
    private void exceptionHandle(CompletableFuture<String> future) {
        future.exceptionally(e -> {
            log.info("error, case: {}", e.getMessage());
            return e.getMessage();
        }).handle((result, e) -> {
            log.info(result);
            return e.getMessage();
        }).whenComplete((result, e) ->
                log.info("result: {}, error msg: {}", result, e.getMessage()));
        log.info(future.join());
    }

    /**
     * OR汇聚关系
     */
    private void or() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            sleep(3, SECONDS);
            log.info("T1 run start...");
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            log.info("T2: run start");
            sleep(5, SECONDS);
        });
        CompletableFuture<String> f3 = f2.applyToEither(f1, (__) -> "complete");

        log.info(f3.join());
        log.info("main complete...");
    }

    /**
     * 串行关系
     */
    private void then() {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(t1 -> t1 + " World")
                .thenApply((t2) -> t2 + " !");
        log.info("result: {}", f.join());
    }

    private void sleep(long time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            log.info("sleep interrupt...");
        }
    }
}

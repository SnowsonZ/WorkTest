package com.learn.snnipet.base.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 异步执行工具
 *
 * @author Snowson
 * @since 2019/6/12 10:19
 */
@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest instance = new CompletableFutureTest();
        instance.combine();
        instance.or();
        instance.then();
        instance.parallelDo();
    }

    /**
     * AND汇聚关系
     */
    private void combine() {
        var f1 = CompletableFuture.runAsync(() -> log.info("T1 洗水壶..."))
                .thenRunAsync(() -> log.info("T1: 烧开水..."));

        var f2 = CompletableFuture.runAsync(() -> log.info("T2 洗茶壶..."))
                .thenRunAsync(() -> log.info("T2 洗茶杯..."))
                .thenRunAsync(() -> log.info("T2 拿茶叶..."))
                .thenApplyAsync(__ -> "龙井");

        CompletableFuture<Void> f3 = f1.thenCombine(f2, (__, tf) ->
                CompletableFuture.runAsync(() -> log.info("T3 拿到茶叶: {}", tf))
                        .thenRunAsync(() -> log.info("T3 煮茶..."))
                        .thenRunAsync(() -> log.info("T3 上茶: {}", tf))
                        .join());
        f3.join();
    }

    public void parallelDo() {
        final CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> log.info("step 1"));
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> log.info("step 2"));
        CompletableFuture<Void> c3 = CompletableFuture.runAsync(() -> log.info("step 3"));

        CompletableFuture.allOf(c1, c2, c3).join();
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

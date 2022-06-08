package com.learn.snnipet.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/6/12 17:41
 */
@Slf4j
public class LambdaTest {
    public static void main(String[] args) {
        Runnable t = () -> log.info("test start...");
        Supplier<String> s = () -> "result";
        Consumer<String> c = (p) -> log.info("{}", p);
        Function<String, String> f = (p) -> p;
        LambdaTest instance = new LambdaTest();
        instance.run(t);
        instance.supplier(s);
        instance.consumer(c);
        instance.function(f);
    }

    private void run(Runnable t) {
        t.run();
    }

    private String supplier(Supplier<String> s) {
        return s.get();
    }

    private void consumer(Consumer<String> c) {
        c.accept("consumer");
    }

    private String function(Function<String, String> f) {
        return f.apply("function");
    }
}

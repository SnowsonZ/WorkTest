package com.test.test.collection;

import com.google.common.collect.ImmutableList;

import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.K;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/11 13:25
 * @Description:
 */
@Slf4j
public class ListTest {
    public static void main(String[] args) {
        ImmutableList<String> list = ImmutableList.of("one", "two", "three");
        String[] array = list.toArray(new String[]{});
        log.info("array: {}, {}, {}", array[0], array[1], array[2]);


    }

    static class WeiBo {
        public void support(BiConsumer<K, K> t) {
            K a = null;
            K b = null;
            t.accept(a, b);
        }

        public void single(Consumer<T> t) {
            T a = null;
            t.accept(a);
        }

        public static WeiBo create(Supplier<WeiBo> supplier) {
            return supplier.get();
        }

        public void test(T content) {

        }

        public WeiBo together() {
            return null;
        }

        public static void main(String[] args) {
            WeiBo weiBo = WeiBo.create(WeiBo::new);
            weiBo.single(weiBo::test);
//            weiBo.single(WeiBo::together);
        }

    }
}

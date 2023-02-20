package com.learn.snnipet.base.generic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 泛型 Test
 * 1. * 限定符使用: extends、super
 * 2. * 通配符捕获
 * 3. 虚拟机中的泛型类型信息(泛型实例化后获取泛型类的信息)
 *
 * @author Snowson
 * @since 2019/3/2 22:46
 */
@Slf4j
public class GenericTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final DateInterval instance = new DateInterval();
        instance.getTemp(100D);

        instance.getOne(Arrays.asList(1.0D, 2.0D, 3.0D));

        final Pair<List<Number>> p = new Pair<>();
        new GenericTest().printTypeInfo(p);
    }

    /**
     * 验证获取泛型类型
     *
     * @param t
     * @param <T>
     */
    public <T> void printTypeInfo(T t) {
        final Class<?> clazz = t.getClass();
        final TypeVariable<? extends Class<?>>[] types = clazz.getTypeParameters();
        for (int i = 0; i < types.length; i++) {
            log.info(types.toString());
        }
        final Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            methods[i].setAccessible(true);
            if (methods[i].getName().equals("xxxx")) {
                final Type type = methods[i].getGenericReturnType();
                log.info(type.toString());
            }
        }
    }
}

class DateInterval extends Pair<List<Number>> {


}

class Pair<T extends Collection<? super Long>> {
    T first;
    T second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public <K extends Number> K getTemp(K k) {
        return k;
    }

    public <M> List<M> xxxx() {
        return Collections.emptyList();
    }

    public void getOne(List<?> data) {
        // 泛型捕获通配符
        System.out.println(getOne1(data));
    }

    public <K> K getOne1(List<K> k) {
        return k.get(0);
    }
}


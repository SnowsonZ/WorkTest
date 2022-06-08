package com.learn.snnipet.annotation;

import java.lang.reflect.Field;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/9/25 10:24
 */
@Data
@Slf4j
public class AnnoTest {
    private Number num;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final AnnoTest instance = new AnnoTest();
        instance.setNum(new Long(123));
        final Class<AnnoTest> clazz = AnnoTest.class;
        final Field field = clazz.getDeclaredField("num");
        field.setAccessible(true);
        final Object result = field.get(instance);
        log.info("{}", result == instance.getNum());
    }


}


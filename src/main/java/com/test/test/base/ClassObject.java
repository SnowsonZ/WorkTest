package com.test.test.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/16 22:51
 * @Description:
 */
@Slf4j
@Data
@ToString
public class ClassObject {
    String name;
    String date;

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<ClassObject> clazz = ClassObject.class;
        log.info("resName: {}", clazz.getName());
        log.info("typeName: {}", clazz.getTypeName());
        log.info("typeParameters: {}", (Object[]) clazz.getTypeParameters());
        log.info("classLoader: {}", clazz.getClassLoader());
        log.info("constructors: {}", (Object[]) clazz.getConstructors());
        log.info("fields: {}", (Object[]) clazz.getDeclaredFields());
        log.info("methods: {}", (Object[]) clazz.getDeclaredMethods());
        log.info("methods: {}", clazz.getDeclaredMethod("getName1", String.class));

        Class<?> classObject = Class.forName("com.test.test.base.ClassObject");
        Constructor<?> constructor = classObject.getConstructors()[0];
        Method getName1 = classObject.getMethod("getName1", String.class);
        getName1.setAccessible(true);
        ClassObject instance = (ClassObject) constructor.newInstance();
        instance.setName("inflect build...");
        instance.setDate(LocalDateTime.now().toString());
        //reflect invoke
        log.info("reflect getName1: {}", getName1.invoke(instance, "reflect invoke"));
        log.info("instance: {}", instance);

    }

    public int getName1(String name) {
        return 0;
    }
}

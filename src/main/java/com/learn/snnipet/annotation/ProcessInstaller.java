package com.learn.snnipet.annotation;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Snowson
 * @date 2020/9/25 10:28
 */
public class ProcessInstaller {
    public static void addProcess(Object obj) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Class<?> clazz = obj.getClass();
        final Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            final PostProcess a = m.getAnnotation(PostProcess.class);
            if (a != null) {
                final Field field = clazz.getDeclaredField(a.source());
                field.setAccessible(true);
                addListener(field.get(obj), obj, m);
            }
        }
    }

    private static void addListener(Object source, Object param, Method m)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        final InvocationHandler handler = (proxy, method, args) -> m.invoke(param);

        final Object listener = Proxy.newProxyInstance(null, new Class[]{ActionListener.class}, handler);
        final Method method = source.getClass().getMethod("addActionListener", ActionListener.class);
        method.invoke(source, listener);
    }
}

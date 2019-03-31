package com.test.test.Generic;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/2 22:46
 */
@Slf4j
public class GenericTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Generic generic = new Generic();
        String[] result = generic.getValue("one", null, "test");
        log.info("result: {}", Arrays.toString(result));
        Comparable[] value = generic.<Comparable>getValue(2.13, 12, 31);
        log.info("value: {}", Arrays.toString(value));
        int compareValue = generic.compareTo(3, 6);
        log.info("compareValue: {}", compareValue);

        /**
         * 构建类型变量实例
         */
        String instance = generic.generateT(String.class);
        log.info("instance: {}", instance);

        /**
         * 实例化类型数组
         */
        /**
         * 错误
         */
//        generic.setElements(ImmutableList.of("123", "456").toArray(new String[]{}));
//        log.info("T[]: {}, obj: {}", generic.getPart(), generic.getElements());
//
//        generic.setElements(new Object[]{123, 423, 412});
//        log.info("T[]: {}, obj: {}", generic.getPart(), generic.getElements());
//
//        String[] array = generic.getPart(ImmutableList.of("123", "456").toArray(new String[]{}));
//        log.info("array: {}", Arrays.toString(array));

        /**
         * 正确
         */
        Integer[] array2 = generic.getArray(123, 452, 123);
        log.info("array2: {}, typeName: {}", Arrays.toString(array2), array2.getClass().getTypeName());

        /**
         * arrayList toArray method
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("429");
        Object[] objs = list.toArray();
        Comparable[] comparables = list.toArray(new Comparable[]{});
        log.info("objs: {}, lists: {}", objs, comparables);

        /**
         * 继承规则
         */
        Comparable[] t;
        Integer[] integers = new Integer[10];
        integers[0] = 12;
        integers[1] = 12;
        t = integers;
        log.info("t: {}", Arrays.toString(t));
        // error
//        ArrayList<Comparable> coms;
//        coms = new ArrayList<Integer>();

        /**
         * ? super Class
         */
        generic.setSuper(new ArrayList<Serializable>());

        /**
         * 反射, 获取参数类型信息
         */
        TypeVariable<? extends Class<? extends Generic>>[] typeParameters = generic.getClass().getTypeParameters();
        log.info("typeParameters: {}", Arrays.toString(typeParameters));
        Type genericSuperclass = generic.getClass().getGenericSuperclass();
        log.info("genericSuperclass: {}", genericSuperclass);
        Type[] genericInterfaces = generic.getClass().getGenericInterfaces();
        log.info("genericInterfaces: {}", genericInterfaces);

        Method[] methods = generic.getClass().getDeclaredMethods();
        log.info("methods: {}", methods);
        for (Method m : methods) {
            TypeVariable<Method>[] tv = m.getTypeParameters();
            log.info("tv: {}", Arrays.toString(tv));
        }

        if (typeParameters.length > 0) {
            String name = typeParameters[0].getName();
            String typeName = typeParameters[0].getTypeName();
            Type[] bounds = typeParameters[0].getBounds();
            log.info("name: {}, typeName: {}, bounds: {}", name, typeName, Arrays.toString(bounds));
        }
    }
}

@Data
class Generic {

    private Object[] elements;
    private T[] part;

    public <T> T[] getValue(T... content) {
        return content;
    }

    /**
     * 类型限定服保持extends类单继承
     *
     * @param b
     * @param a
     * @param <T>
     * @return
     */
    public <T extends Comparable> int compareTo(T b, T a) {
        return a.compareTo(b);
    }

    @Override
    protected Generic clone() throws CloneNotSupportedException {
        return (Generic) super.clone();
    }

    public <T> T generateT(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public void setElements(T[] array) {
        elements = array;
    }

    public void setElements(Object[] obj) {
        part = (T[]) obj;
    }

    public <T extends Comparable> T[] getPart(T... array) {
        Object[] objs = new Object[2];
        return (T[]) objs;
    }

    public T[] getPart() {
        return part;
    }

    public <T extends Comparable> T[] getArray(T... a) {
        return (T[]) Array.newInstance(a.getClass().getComponentType(), 3);
    }

    public void setSuper(List<? super Number> param) {

    }

}

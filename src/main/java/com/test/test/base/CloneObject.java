package com.test.test.base;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/12/16 22:24
 * @Description: 1. clone为浅拷贝 2. 需实现Cloneable
 */
@ToString
@Data
@Slf4j
public class CloneObject implements Cloneable {
    String name;
    float price;
    CloneObject obj;

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneObject obj1 = new CloneObject();
        CloneObject childFrom1 = new CloneObject();
        childFrom1.setName("obj1 child");
        obj1.setObj(childFrom1);
        CloneObject child2From1 = new CloneObject();
        childFrom1.setObj(child2From1);
        CloneObject obj2 = (CloneObject) obj1.clone();
        obj1.setName("obj1");
        obj2.setName("obj2");
        //证明是浅拷贝，指向同一个对象
        obj2.getObj().setName("obj2");
        log.info("obj1 value: {}", obj1);
        log.info("obj2 value: {}", obj2);

    }
}

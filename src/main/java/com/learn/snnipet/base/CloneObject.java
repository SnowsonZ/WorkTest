package com.learn.snnipet.base;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2018/12/16 22:24
 * <p>
 * 1. clone为浅拷贝
 * <p>
 * 2. 需实现Cloneable
 * <p>
 * 3. 实现深拷贝方法 1. 序列化 2. 本示例, 父clone方法中，调用子clone，并赋值给父
 */
@ToString
@Data
@Slf4j
public class CloneObject implements Cloneable {
    String name;
    float price;
    ChildObject obj;

    public static void main(String[] args) {
        CloneObject parent = new CloneObject();
        parent.setName("parent_1");
        parent.setPrice(10);
        ChildObject child = new ChildObject();
        child.setName("child_1");
        child.setType(0);
        parent.setObj(child);

        CloneObject uncle = parent.clone();
        uncle.setName("uncle_1");
        uncle.setPrice(7);
        uncle.getObj().setName("child_2");
        uncle.getObj().setType(1);

        log.info("parent: {}", parent);
        log.info("uncle: {}", uncle);
    }

    @Override
    public CloneObject clone() {
        CloneObject obj;
        try {
            obj = (CloneObject) super.clone();
            obj.obj = obj.getObj().clone();
            return obj;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

@Slf4j
@Data
class ChildObject implements Cloneable {
    private String name;
    private int type;

    @Override
    public ChildObject clone() {
        try {
            return (ChildObject) super.clone();
        } catch (CloneNotSupportedException e) {
            log.info("clone error, msg: {}", e.getMessage());
        }
        return null;
    }
}

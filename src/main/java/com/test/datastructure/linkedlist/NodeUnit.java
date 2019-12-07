package com.test.datastructure.linkedlist;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

/**
 * @author Snowson
 * @since 2019/3/31 13:15
 */
@Data
public class NodeUnit<T extends Number> implements Serializable {
    private static final long serialVersionUID = -3002025537567851082L;

    private T content;
    private NodeUnit<T> prev;
    private NodeUnit<T> next;

    public T withContent(T t) {
        ArrayList<Number> numbers = new ArrayList<>();
        NodeUnit<Integer> unit = new NodeUnit<>();
        return t;
    }
}

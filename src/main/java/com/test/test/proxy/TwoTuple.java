package com.test.test.proxy;

import lombok.Data;

/**
 * @author Snowson
 * @since 2019/11/15 16:44
 */
@Data
public class TwoTuple<T, M> {
    private T first;
    private Class<M> second;


}

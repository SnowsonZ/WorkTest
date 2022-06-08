package com.learn.snnipet.proxy.dynamic;

/**
 * @author Snowson
 * @date 2020/5/3 17:01
 */
public interface GoodsQueryService {
    int queryOne(long id);

    boolean updateOne(long id);

    void print();

    void receive(String msg);
}

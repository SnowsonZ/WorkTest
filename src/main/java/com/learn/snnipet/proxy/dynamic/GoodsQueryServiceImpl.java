package com.learn.snnipet.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/5/3 17:02
 */
@Slf4j
public class GoodsQueryServiceImpl implements GoodsQueryService {
    @Override
    public int queryOne(long id) {
        return 0;
    }

    @Override
    public boolean updateOne(long id) {
        return true;
    }

    @Override
    public void print() {
        log.info("method print");
    }

    @Override
    public void receive(String msg) {
        log.info("receive msg: {}", msg);
    }
}

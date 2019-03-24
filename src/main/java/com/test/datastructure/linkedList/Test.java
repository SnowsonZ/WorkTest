package com.test.datastructure.linkedList;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/22 11:53
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        Node result = Node.createNodeList(new String[]{"a", "b", "c", "q", "a"});
//        log.info("nodeList: {}", JSON.toJSONString(result));

        boolean flag = Node.isBackToText(result);
        log.info("isBackToText flag: {}", JSON.toJSONString(flag));

//        Node reverse = Node.reverse(result);
//        log.info("reverse: {}", JSON.toJSONString(reverse));
    }
}

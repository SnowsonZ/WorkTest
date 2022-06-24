package com.learn.utils.tester;

import com.alibaba.fastjson.JSON;
import com.learn.datastructure.linkedlist.Node;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 **/
@Slf4j
public class LinkedTester {
    public static void main(String[] args) {
        Node result = Node.createNodeList(new String[]{"a", "b", "c", "d", "d", "e", "f", "z", "f", "e", "d", "c", "b", "a"});
        log.info("nodeList: {}", JSON.toJSONString(result));

        // 回文判断
        boolean flag = Node.isBackToText(result);
        log.info("isBackToText flag: {}", JSON.toJSONString(flag));

        // 列表反转
        Node reverse = Node.reverse(result);
        log.info("reverse: {}", JSON.toJSONString(reverse));

        // 创建带环链表
        Node circle = Node.createCircleNodeList(new String[]{});
        boolean isCircle = Node.isCircle(circle);
        log.info("isCircle: {}", isCircle);
    }
}

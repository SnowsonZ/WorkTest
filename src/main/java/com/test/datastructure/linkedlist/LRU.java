package com.test.datastructure.linkedlist;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 单链表实现的简易LRU缓存
 *
 * @author Snowson
 * @since 2019/12/3 22:58
 */
@Slf4j
@Data
public class LRU {
    private static final int CACHE_LENGTH_LIMIT = 5;
    private int currentLength;
    private Page head;

    public LRU() {
        head = new Page(null, -1, null);
        Page guard = new Page(null, -1, null);
        head.setNext(guard);
    }

    private void calculate(List<String> pages) {
        for (String page : pages) {
            put(page);
        }
    }

    public void put(String target) {
        Page temp = head.getNext();
        Page cacheLength = null;
        while (temp.getNext() != null) {
            Page current = temp.getNext();
            if (current.getName().equals(target)) {
                current.setVisit(current.getVisit() + 1);
                temp.setNext(current.getNext());
                Page first = head.getNext().getNext();
                current.setNext(first);
                head.getNext().setNext(current);
                return;
            }
            cacheLength = temp;
            temp = temp.getNext();
        }
        if (cacheLength != null && currentLength >= CACHE_LENGTH_LIMIT) {
            cacheLength.setNext(null);
            currentLength--;
        }
        Page first = head.getNext().getNext();
        Page newFirst = new Page(target, 1, first);
        head.getNext().setNext(newFirst);
        currentLength++;
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        lru.calculate(Arrays.asList("1", "2", "3", "4", "5", "4", "2", "4", "2", "3", "9", "10", "1"));
        log.info("{}", JSON.toJSONString(lru.getHead()));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Page {
    private String name;
    private Integer visit;
    private Page next;
}
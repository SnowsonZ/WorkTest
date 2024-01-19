package com.learn.utils.tester;

import com.learn.datastructure.linkedlist.TrieTree;
import com.learn.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Snowson
 **/
@Slf4j
public class TrieTreeTester {
    public static void main(String[] args) {
        final TrieTree trieTree = new TrieTree();
        final List<String> src = DataGenerator.stringList(2, 50, 3);
        System.out.println(src);
        for (String ele : src) {
            trieTree.add(ele);
        }
        final int index = src.size() - (int) (Math.random() * src.size());
        final String target = src.get(index);
        log.info("index: {}, content: {}", index, target);
        System.out.println(trieTree.exists(target));
        System.out.println(trieTree.countPrefix(target));
        System.out.println(trieTree.countTarget(target));
        trieTree.delete(target);
        System.out.println(trieTree.countTarget(target));
    }
}

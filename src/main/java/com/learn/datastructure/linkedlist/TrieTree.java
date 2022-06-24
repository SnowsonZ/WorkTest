package com.learn.datastructure.linkedlist;

import lombok.Data;

/**
 * 前缀树
 * <p>
 * 用于统计字符串/字符串前缀出现的次数
 *
 * @author Snowson
 */
public class TrieTree {
    /**
     * The Head.
     */
    Node head;

    /**
     * Instantiates a new Trie tree.
     */
    public TrieTree() {
        head = new Node();
        head.pass = 0;
        head.end = 0;
        head.next = new Node[26];
    }

    /**
     * 前缀树的节点
     */
    @Data
    static class Node {
        /**
         * 字符串包含该字符
         */
        int pass;
        /**
         * 字符串以该字符结尾
         */
        int end;
        /**
         * 指向下一个节点的节点数组
         */
        Node[] next;
    }

    /**
     * 加入字符串{str}
     *
     * @param str the str
     */
    public void add(String str) {
        if (isNullOrEmpty(str)) return;
        final char[] array = str.toCharArray();
        Node pointer = head;
        pointer.pass++;
        for (char c : array) {
            final int index = c - 'a';
            Node node = pointer.next[index];
            if (node == null) {
                node = new Node();
                node.pass = 0;
                node.end = 0;
                node.next = new Node[26];
                pointer.next[index] = node;
            }
            node.pass += 1;
            pointer = node;
        }
        pointer.end += 1;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 删除字符串{str}
     *
     * @param str the str
     */
    public void delete(String str) {
        if (isNullOrEmpty(str)) return;
        if (!exists(str)) return;
        Node pointer = head;
        final char[] array = str.toCharArray();
        for (char c : array) {
            final int index = c - 'a';
            if (pointer.next[index] == null) return;
            pointer.next[index].pass -= 1;
            if (--pointer.next[index].pass <= 0) {
                pointer.next[index] = null;
                return;
            }
            pointer = pointer.next[index];
        }
        pointer.end--;
    }

    /**
     * {str}是否存在
     *
     * @param str the str
     * @return the boolean
     */
    public boolean exists(String str) {
        if (isNullOrEmpty(str)) return false;
        Node pointer = head;
        final char[] array = str.toCharArray();
        for (char c : array) {
            final int index = c - 'a';
            if (pointer.next[index] == null) return false;
            pointer = pointer.next[index];
        }
        return pointer.end > 0;
    }

    /**
     * 统计字符串{str}数量
     *
     * @param str the str
     * @return the int
     */
    public int countTarget(String str) {
        if (isNullOrEmpty(str)) return 0;
        Node pointer = head;
        final char[] array = str.toCharArray();
        for (char c : array) {
            final int index = c - 'a';
            if (pointer.next[index] == null) return 0;
            pointer = pointer.next[index];
        }
        return pointer.end;
    }

    /**
     * 统计以{str}为前缀的字符串数量
     *
     * @param str the str
     * @return the int
     */
    public int countPrefix(String str) {
        if (isNullOrEmpty(str)) return 0;
        Node pointer = head;
        final char[] array = str.toCharArray();
        for (char c : array) {
            final int index = c - 'a';
            if (pointer.next[index] == null) return 0;
            pointer = pointer.next[index];
        }
        return pointer.pass;
    }
}

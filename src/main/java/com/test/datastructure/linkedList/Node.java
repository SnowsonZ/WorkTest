package com.test.datastructure.linkedList;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Node.
 *
 * @author Snowson
 * @since 2019 /3/22 11:52
 */
@Data
@Slf4j
public class Node implements Serializable {
    private static final long serialVersionUID = 124107946907887244L;

    private String content;
    private Node next;
    private Node prev;

    /**
     * Create node list node.
     *
     * @param array the array
     * @return the node
     */
    public static Node createNodeList(String[] array) {
        if (array.length <= 0) {
            log.info("original data is null");
            return null;
        }
        //创建头结点
        Node head = new Node();
        Node flag = new Node();
        flag.next = head;
        for (String item : array) {
            Node node = new Node();
            node.setContent(item);
            flag.next.next = node;
            flag.next = node;
        }

        return head;
    }

    /**
     * 链表反转（带头结点）
     *
     * @param node the node
     * @return node
     */
    public static Node reverse(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return node;
        }
        Node flag, visit, temp = node.next;
        flag = temp.next;
        visit = flag.next;

        // 第一个节点在反转后作为尾节点
        temp.next = null;
        while (visit != null) {
            // 链表反转
            flag.next = temp;
            // 缓存节点，待下次反转
            temp = flag;
            // 移动到下一个待反转的节点
            flag = visit;
            // 移动到剩余正向链表的头节点
            visit = visit.next;
        }
        // 循环完毕后，最后一个节点的反转未完成
        flag.next = temp;
        // 头结点指向第一个节点
        node.next = flag;
        return node;
    }

    /**
     * 判断字符串是否为回文（带头结点）
     *
     * @param node the node
     * @return boolean
     */
    public static boolean isBackToText(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return false;
        }

        // 初始化反转头结点, 慢指针,快指针为第一步移动的结果
        Node temp = node.next, slow = temp.next, quick = slow.next, flag = slow;

        // 反转后,原来的第一个元素将作为尾节点，故next为null
        temp.next = null;

        // 字符串长度为2
        if (quick == null) {
            return temp.getContent().equals(slow.getContent());
        }

        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            flag.next = temp;
            temp = flag;
            quick = quick.next.next;
            flag = slow;
        }
        // 先移动慢指针，再将反转的链表的头指针归位，否则慢指针将断开正向的链表
        slow = slow.next;
        flag.next = temp;

        if (quick.next == null) {
            flag = flag.next;
        }
        boolean isBackText = true;
        while (slow != null && flag != null) {
            if (!slow.getContent().equals(flag.getContent())) {
                isBackText = false;
            }
            slow = slow.next;
            flag = flag.next;
        }
        if (slow != null || flag != null) {
            isBackText = false;
        }

        return isBackText;
    }
}

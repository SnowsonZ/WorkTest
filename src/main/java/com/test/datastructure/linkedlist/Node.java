package com.test.datastructure.linkedlist;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Node.
 * <p>
 * TODO 2.两个有序链表合并     3.删除链表倒数第 n 个结点    4.求链表的中间结点  5.相交链表交点  6.入环点  7. 哨兵   8.LRU实现
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
        Node flag = head;
        for (String item : array) {
            Node node = new Node();
            node.setContent(item);
            flag.next = node;
            flag = node;
        }

        return head;
    }

    public static Node createCircleNodeList(String[] array) {
        if (array.length <= 0) {
            log.info("original data is null");
            return null;
        }
        //创建头结点
        Node head = new Node();
        Node flag = head;
        for (String anArray : array) {
            Node node = new Node();
            node.setContent(anArray);
            flag.next = node;
            flag = node;
        }
        flag.next = head;

        return head;
    }

    /**
     * 链表反转（带头结点）
     *
     * @param node the node
     * @return node node
     */
    public static Node reverse(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return node;
        }
        Node flag;
        Node visit;
        Node temp = node.next;
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
     * <br>
     * <b>Note</b> 边界检测： 节点为空, 链表长度为1, 2, 3
     *
     * @param node the node
     * @return boolean boolean
     */
    public static boolean isBackToText(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return false;
        }

        // 初始化反转头结点, 慢指针,快指针为第一步移动的结果
        Node temp = node.next;
        Node slow = temp.next;
        Node quick = slow.next;
        Node flag = slow;

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
        // 先移动慢指针，再将反转的链表的首元素归位，否则正向链表会断开
        slow = slow.next;
        flag.next = temp;

        //若为奇数串,将temp = 中间节点, 偶数串,temp = 正向链表的首节点
        if (quick.next == null) {
            temp = flag;
            flag = flag.next;
            temp.next = slow;
        } else {
            temp = slow;
        }

        boolean isBackText = true;
        // prev用于保存逆向链表，防止断开
        Node prev = flag.next;
        while (slow != null && prev != null) {
            if (!slow.getContent().equals(flag.getContent())) {
                isBackText = false;
            }
            //复原链表
            flag.next = temp;
            temp = flag;
            flag = prev;
            slow = slow.next;
            prev = prev.next;
        }
        // 循环中没有进行最后一次判断
        if (slow != null && !slow.getContent().equals(flag.getContent())) {
            isBackText = false;
        }
        flag.next = temp;
        log.info("src linked list: {}", JSON.toJSONString(node));
        return isBackText;
    }

    /**
     * Is circle boolean.
     * 判断链表是否含有环
     *
     * <br>
     * <b>Node</b> V2 - V1 = nC。 V1,V2为快慢指针速度,C为节点个数（周长）只要有环, 两指针总能相遇, 并且快指针比慢指针多走V2-V1圈
     *
     * @param node the node
     * @return boolean
     */
    public static boolean isCircle(Node node) {
        if (node == null) {
            return false;
        }
        if (node.next == node) {
            return true;
        }
        Node slow = node.next;
        Node quick = node.next;
        boolean isCircle = true;
        do {
            if (quick.next == null || quick.next.next == null) {
                isCircle = false;
                break;
            } else {
                slow = slow.next;
                quick = quick.next.next;
            }
        }while (slow != quick);

        return isCircle;
    }
}

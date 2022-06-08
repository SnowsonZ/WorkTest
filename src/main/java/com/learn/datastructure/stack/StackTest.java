package com.learn.datastructure.stack;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Snowson
 * @since 2019/12/8 15:11
 */
public class StackTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        boolean valid = new StackTest().isValid("{{{[(())]}}}");
        System.out.println(valid);
    }

    public Map<Character, Character> mapping;

    public StackTest() {
        this.mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');
    }

    public boolean isValid(String s) {
        Stack<Character> container = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (this.mapping.containsKey(c)) {
                char topElement = container.isEmpty() ? '@' : container.pop();
                if (topElement != this.mapping.get(c)) {
                    return false;
                }
            } else {
                container.push(c);
            }
        }
        return container.isEmpty();
    }
}

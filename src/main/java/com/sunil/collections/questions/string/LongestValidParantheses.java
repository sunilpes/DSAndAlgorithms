package com.sunil.collections.questions.string;

import java.util.*;

public class LongestValidParantheses {

    public int find(String s) {
        if (s.length() <= 1) return 0;
        String[] parentheses = s.split("");
        Deque<Integer> stack = new ArrayDeque<>();
        int longest = 0;

        for(int i = 0; i < parentheses.length; i++) {
            if (parentheses[i].equals("(")) {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                int left = stack.peek();
                if (parentheses[left].equals("(")) {
                    stack.poll();
                } else {
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }

        if (stack.isEmpty()) return parentheses.length;
        else {
            int a = parentheses.length, b = 0;
            while(!stack.isEmpty()) {
                b = stack.poll();
                longest = Math.max(longest, a-b-1);
                a = b;
            }
            longest = Math.max(longest, a);
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestValidParantheses lvp = new LongestValidParantheses();
        System.out.println("result : " + lvp.find("()()((()(("));
    }
}

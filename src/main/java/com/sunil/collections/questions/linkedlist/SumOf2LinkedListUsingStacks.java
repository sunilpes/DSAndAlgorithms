package com.sunil.collections.questions.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumOf2LinkedListUsingStacks {

    public static void main(String[] args) {
        int sum = 0;
        int carry = 0;
        Node head = null;
        Node result = null;

        // a = 99987
        Node a = new Node(9, true);
        a.add(9).add(9).add(8).add(7);

        // b= 476
        Node b = new Node(4, true);
        b.add(7).add(6);

        // sum = 99987 + 476 = 100463

        int length = Math.max(a.size(), b.size());

        // to have 1 extra carry forward in the total sum; e.g 999 + 999 = (1)998
        if (a.size() == b.size()
                || (a.size() > b.size() && a.head() == 9)
                || (b.size() > a.size() && b.head() == 9)) {
            length = length+1;
        }

        Deque<Integer> stackA = new ArrayDeque<Integer>(length);
        Deque<Integer> stackB = new ArrayDeque<Integer>(length);

        for(int s =0; s<length;s++) {
            if ((length-a.size()) <= s && a != null) {
                stackA.push(a.data);
                a = a.next();
            } else {
                stackA.push(0);
            }

            if ((length-b.size()) <= s && b != null) {
                stackB.push(b.data);
                b = b.next();
            } else {
                stackB.push(0);
            }
        }


        System.out.println("A: "+ stackA);
        System.out.println("B: "+ stackB);


        for (int i = 0; i< length; i++) {
            sum = carry + stackA.pop() + stackB.pop();
            carry = sum > 9 ? 1 : 0;
            int partial_sum = (sum % 10);
            head = (result == null) ? new Node(partial_sum, true) : head;
            result = (result == null) ? head: result.add(partial_sum);
        }

        assert head != null;
        head.reverseList().traverse();

    }
}

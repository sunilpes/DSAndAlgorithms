package com.sunil.collections.questions.stack;

import java.util.LinkedList;
import java.util.*;

/**
 * https://www.geeksforgeeks.org/queue-using-stacks/
 */

public class QueueUsing2Stacks {

    /**
     * https://www.geeksforgeeks.org/static-class-in-java/
     */
    public static class Queue {

        private List<Integer> inbox = new LinkedList<>(); // stack1
        private List<Integer> outbox = new LinkedList<>(); // stack2


        public void enQueue(int item) {
            inbox.add(item);
        }

        public int deQueue() {
            if (outbox.isEmpty()) {
                outbox.addAll(inbox);
                inbox.clear();
            }
            return outbox.remove(0);
        }

    }

    public static void main(String[] args) {
        Queue q1 = new Queue();
        q1.enQueue(3);
        q1.enQueue(4);
        q1.enQueue(5);
        q1.enQueue(6);
        q1.enQueue(7);
        System.out.println(q1.deQueue());
        System.out.println(q1.deQueue());
        System.out.println(q1.deQueue());
        System.out.println(q1.deQueue());
        System.out.println(q1.deQueue());
        q1.enQueue(8);
        q1.enQueue(9);
        System.out.println(q1.deQueue());
        System.out.println(q1.deQueue());
        q1.enQueue(10);
        System.out.println(q1.deQueue());
    }
}

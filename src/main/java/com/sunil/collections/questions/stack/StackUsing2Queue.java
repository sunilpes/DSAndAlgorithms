package com.sunil.collections.questions.stack;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://www.geeksforgeeks.org/implement-stack-using-queue/
 */

public class StackUsing2Queue {

    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    static class Stack {

        public void push(int x) {
            if (q2.isEmpty()) {
                q2.offer(x);

                while(!q1.isEmpty()) {
                    q2.offer(q1.poll());
                }

                Queue<Integer> temp = q1;
                q1= q2;
                q2= temp;
            }

        }

        public Integer pop() {
            return q1.poll();
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}

package com.sunil.collections.questions.linkedlist;

import com.sunil.collections.questions.linkedlist.DetectCycle;

public class MutilevelLinkedList {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        Node(int data) {
           this.val = data;
        }

        Node(int data, Node child) {
            this.val = data;
            this.child = child;
        }

        public Node add(int data) {
            Node next = new Node(data);
            this.next = next;
            next.prev = this;
            return next;
        }

        public Node add(int data, Node child) {
            Node next = new Node(data);
            this.next = next;
            next.prev = this;
            next.child = child;
            return next;
        }

        public Node child(Node node) {
            this.child = node;
            return child;
        }

        public void traverse() {
            Node that = this;
            while(that != null) {
                System.out.println(that.val);
                that = that.next;
            }
        }

    }

    public Node flattenRecur(Node head, Node parentLevel) {
        if (head == null) return head;

        while(head != null) {
            System.out.println(head.val);
            Node child = head.child;
            if (child != null) {
                Node level = head.next;
                head.next = child;
                child.prev = head;
                head.child = null;
                if (level == null) {
                    level = parentLevel;
                    parentLevel = null;
                }
                flattenRecur(child, level);
                head = level;
            }

            if(head != null) {
                if (head.next == null && parentLevel != null) {
                    head.next = parentLevel;
                    parentLevel.prev = head;
                    break;
                }
                head = head.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MutilevelLinkedList mll = new MutilevelLinkedList();

//        Node n3 = new Node(11);
//        n3.add(12);
//
//        Node n2 = new Node(7);
//        n2.add(8, n3).add(9).add(10);
//
//        Node n = new Node(1);
//        n.add(2).add(3, n2).add(4).add(5).add(6);

        // [1,2,3,7,8,11,12,9,10,4,5,6]

        Node n3 = new Node(11);
        n3.add(12);

        Node n2 = new Node(7);
        n2.add(8, n3);

        Node n = new Node(1);
        n.add(2).add(3, n2).add(4).add(5).add(6);



        mll.flattenRecur(n, null);
        System.out.println("-------------");
        n.traverse();

    }
}

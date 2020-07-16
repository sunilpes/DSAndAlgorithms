package com.sunil.collections.questions.linkedlist;

public class Node {
    int data;
    Node left;
    Node right;
    boolean isRoot;

    Node(int data, boolean isRoot) {
        this.data = data;
        this.isRoot = isRoot;
    }

    public Node add(int data) {
        Node next = new Node(data, false);
        this.right = next;
        next.left = this;
        return next;
    }

    public void traverse() {
        Node that = this;
        while(that != null) {
            System.out.println(that.data);
            that = that.right;
        }
    }

}

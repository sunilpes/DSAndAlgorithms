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
            System.out.print(that.data);
            that = that.right;
        }
    }

    public int head() {
        return this.data;
    }

    public Node next() {
        return this.right;
    }

    public Node prev() {
        return this.left;
    }

    public void checkStructure() {
        if (this.isRoot) {
            assert(this.prev() == null);
            assert(this.next() != null);
        } else {
            assert(this.prev() != null);
            assert(this.next() != this.prev());
        }

        if(this.next() != null) {
            this.next().checkStructure();
        }
    }

    private int size(Node that) {
        if (that == null) {
            return 0;
        }
        return 1 + size(that.next());
    }

    public int size() {
        return size(this);
    }

    public Node reverseList() {
        Node curr = this;
        Node prev = null;
        Node next = null;

        while(curr != null) {
            next = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}

package com.sunil.collections.questions.linkedlist;

public class FindLength {

    public static int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.next());
    }

    public static void main(String[] args) {
        Node root = new Node(1, true);
        root.add(2).add(3).add(4).add(5).add(6);
        System.out.println("size: "+size(root));
    }
}

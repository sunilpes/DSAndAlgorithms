package com.sunil.collections.questions.linkedlist;

/**
 * Reverse a given linkedList
 */
public class reverseLinkedList {

    public static Node reverseList(Node node) {
        Node curr = node;
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

    public static void main(String[] args) {
        Node root = new Node(1, true);
        root.add(2);

        Node reversedList = reverseList(root);
        reversedList.traverse();
    }
}

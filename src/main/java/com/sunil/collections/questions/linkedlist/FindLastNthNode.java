package com.sunil.collections.questions.linkedlist;

/**
 * How do you find the third node from the end in a singly linked list?
 *
 */
public class FindLastNthNode {

    public static Node getLastNode(Node head, int n) {
        Node fast = head;
        Node slow = head;
        int start = 1;
        while (fast.next() != null) {
            fast = fast.next();
            start++;
            if (start > n) {
                slow = slow.next();
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        Node node = new Node(1, true);
        node.add(2).add(3).add(4).add(5);

        System.out.println("Last Third Node: " + getLastNode(node,3).data);
    }
}

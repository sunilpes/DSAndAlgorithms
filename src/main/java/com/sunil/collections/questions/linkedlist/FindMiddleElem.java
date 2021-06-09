package com.sunil.collections.questions.linkedlist;

/**
 * LinkedList: Find the middle element in a single pass
 */

public class FindMiddleElem {

    public static Node getMiddleNode(Node node) {
        Node fast = node, slow = node;
        while(fast != null && fast.right !=null) {
            fast = fast.right.right;
            if(fast != null) slow = slow.right;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node root = new Node(1, true);
        root.add(2)
        .add(3)
        .add(4)
        .add(5)
        .add(6)
        .add(7).add(8);

        //root.traverse();

        System.out.println(getMiddleNode(root).data);
    }
}

package com.sunil.collections.questions.linkedlist;

/**
 * LinkedList: Find the middle element in a single pass
 */

public class FindMiddleElem {

    public static Node getMiddleNode(Node node) {
        Node pointer1 = node, pointer2 = node;
        while(pointer1 != null) {
            pointer1 = pointer1.right;
            if (pointer1 != null) {
                pointer1 = pointer1.right;
                if(pointer1 != null) pointer2 = pointer2.right;
            }
        }
        return pointer2;
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

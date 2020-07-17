package com.sunil.collections.questions.linkedlist;

public class DetectCycle {

    public static boolean isCyclic(Node node) {
        Node fast = node;
        Node slow = node;
        while(fast != null && fast.right != null) {
            fast = fast.right.right;
            slow = slow.right;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Node root = new Node(1, true);
        root.add(2).add(3).add(4).add(5).right = root;

        System.out.println("LinkedList is Cyclic? => "+isCyclic(root));

    }
}

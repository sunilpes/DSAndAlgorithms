package com.sunil.collections.questions.linkedlist;

import java.util.HashSet;

/**
 * removed duplicate element from LinkedList
 */
public class removeDuplicate {

    public static Node remove(Node node) {
        Node root = node;
        HashSet<Integer> hs = new HashSet<>();

        while (node != null) {
            if(!hs.contains(node.data)) {
                hs.add(node.data);
            } else {
                // remove this node
                Node next = node.next();
                Node prev = node.prev();
                if(prev != null) prev.right = next;
                if(next != null) next.left = prev;
            }
            node = node.next();
        }
        return root;
    }


    public static void main(String[] args) {
        Node node = new Node(1, true);
        node.add(2).add(3).add(3).add(4).add(4).add(1).add(3);

        Node nodeSet = remove(node);
        nodeSet.traverse();
        nodeSet.checkStructure();
    }
}

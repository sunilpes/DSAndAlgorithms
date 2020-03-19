package com.sunil.collections.tree;

import java.util.*;

class MirrorTree {

    static class Node {
        int data = -1;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }

        // in-order traversal print
        public static void print(Node n) {
            if (n.left !=null) print(n.left);
            System.out.println(n.data);
            if (n.right !=null) print(n.right);
        }
    }

    private static void mirror(Node n) {
        Node temp = n.right;
        n.right = n.left;
        n.left = temp;

        if(n.left != null) mirror(n.left);
        if(n.right != null) mirror(n.right);
    }


    public static void main(String args[] ) throws Exception {

        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int Q = s.nextInt();

        Node[] node = new Node[N + 1];

        for (int i = 0; i < N; i++) {
            int parent = s.nextInt();
            int child = s.nextInt();
            String pos = s.next();

            if (node[parent] == null) {
                node[parent] = new Node(parent);
            }

            if (node[child] == null) {
                node[child] = new Node(child);
            }

            if(pos.equals("L")) {
                node[parent].left = node[child];
            } else {
                node[parent].right = node[child];
            }
        }

        mirror(node[0]);
        Node.print(node[0]);


        // Write your code here

    }
}

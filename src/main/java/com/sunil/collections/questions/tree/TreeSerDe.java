package com.sunil.collections.questions.tree;

import com.sunil.collections.tree.TreeNode;

import java.util.*;

/**
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 */

public class TreeSerDe {


    public static TreeNode<Integer> deserialize(String str) {
        if ("".equals(str) || str.split(",").length == 0) {
            return null;
        }
        List<String> data = new LinkedList<>(Arrays.asList(str.split(",")));
        return fromList(data);
    }

    public static TreeNode<Integer> fromList(List<String> data) {
        String intStr = data.get(0);
        Deque<TreeNode<Integer>> q = new ArrayDeque<>();

        TreeNode<Integer> root = new TreeNode<>(Integer.parseInt(intStr));
        q.offer(root);
        int counter = 1;

        while(counter < data.size()) {
            TreeNode<Integer> node = q.poll();

            intStr = data.get(counter);
            node.setLeft(new TreeNode<>(Integer.parseInt(intStr)));
            q.offer(node.left());
            counter++;

            intStr = data.get(counter);
            node.setRight(new TreeNode<>(Integer.parseInt(intStr)));
            q.offer(node.right());
            counter++;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> tree = new TreeNode<>(10);
        TreeNode<Integer> left1 = tree.addToLeft(9);
        left1.addToRight(7);
        left1.addToLeft(6);

        TreeNode<Integer> right1 = tree.addToRight(11);
        right1.addToLeft(13);
        right1.addToRight(12);

        String serializedTree = TreeNode.toString(tree);
        System.out.println("Serialized Tree: " + serializedTree);

        TreeNode<Integer> deserializedTree =  deserialize(serializedTree);
        System.out.println("Deserialized Tree: " + TreeNode.toString(deserializedTree));

        assert serializedTree.equals(TreeNode.toString(deserializedTree));
    }

}

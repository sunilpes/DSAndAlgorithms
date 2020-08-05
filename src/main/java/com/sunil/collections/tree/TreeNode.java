package com.sunil.collections.tree;

import java.util.*;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode(T data) {
       this.data = data;
    }

    public TreeNode<T> addToRight(T data) {
        if (Optional.ofNullable(data).isPresent()) {
            this.right = new TreeNode<T>(data);
        }
        return this.right;
    }

    public TreeNode<T> addToLeft(T data) {
        if (Optional.ofNullable(data).isPresent()) {
            this.left = new TreeNode<T>(data);
        }
        return this.left;
    }

    public TreeNode<T> right() {
        return this.right;
    }

    public TreeNode<T> left() {
        return this.left;
    }

    public void inOrdertraverse(TreeNode<T> node, StringBuilder str) {
        if (node != null) {
            // in-order traversal - left-root-right
            if (node.left() != null) inOrdertraverse(node.left(), str);
            str.append(node.data).append(",");
            if(node.right() != null) inOrdertraverse(node.right(), str);
        }
    }

    public String traverse(TreeNode<T> root, StringBuilder str) {
        Deque<TreeNode<T>> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode<T> node = q.poll();
            if (node != null) {
                str.append(node.data).append(",");
                if(node.left() != null) q.offer(node.left());
                if(node.right() != null) q.offer(node.right());
            }
        }
        return str.toString();
    }

    public static <T> String toString(TreeNode<T> node) {
        if (node == null) return "";
        StringBuilder str = new StringBuilder();
        node.traverse(node, str);
        return str.toString();
    }
}

package com.sunil.collections.questions.tree;


import com.sunil.collections.tree.TreeNode;

import java.util.*;

public class RightSideView {
    public List<Integer> getSolution(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        levelMap = traverse(root, 0, levelMap);
        levelMap.forEach((level, nodes) -> {
            System.out.println(level+ ":" + Arrays.toString(nodes.toArray()));
            if (nodes.size() > 0) {
                result.add(nodes.get(0));
            }
        });
        return result;
    }

    private Map<Integer, List<Integer>> traverse(TreeNode root, int level, Map<Integer, List<Integer>> levelMap) {
        if (root == null) return levelMap;

        level += 1;

        if (root.right != null) {
            traverse(root.right, level, levelMap);
        }
        System.out.println("level :" + level + " " + "node: " + root.data);
        if (levelMap.containsKey(level)){
            System.out.println("adding node to existing map:" + root.data);
            List<Integer> levelNodes = levelMap.get(level);
            levelNodes.add((int) root.data);
            levelMap.put(level, levelNodes);
        } else {
            System.out.println("creating new map for level:" + level);
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add((int) root.data);
            levelMap.put(level, levelNodes);
        }

        if (root.left != null) {
            traverse(root.left, level, levelMap);
        }

        return levelMap;
    }

    public static void main(String[] args) {
        TreeNode<Integer> tn = new TreeNode<>(1);
        tn.addToRight(3).addToRight(6);
        TreeNode<Integer> tn2 = tn.addToLeft(2);
        TreeNode<Integer> tn5 = tn2.addToRight(5);
        TreeNode<Integer> tn4 = tn2.addToLeft(4);
        tn5.addToRight(9);
        tn4.addToRight(7).addToLeft(8);


        RightSideView rsv = new RightSideView();
        List<Integer> solution = rsv.getSolution(tn);
        solution.forEach(System.out::println);
    }

}

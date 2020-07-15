package com.sunil.collections.tree;

import sun.jvm.hotspot.ui.SysPropsPanel;

import java.util.Arrays;

public class Btree {

    BtreeNode root;
    int t;
    int minKeys;
    int maxKeys;

    public Btree(int order) {
        this.t = order;
        this.minKeys = order - 1;
        this.maxKeys = order * 2 - 1;
        this.root = new BtreeNode(maxKeys, true);
    }

    class BtreeNode {
        /* should be of order t
         * max no. of keys for all node = 2t - 1
         * min no. of keys for all node = t-1
         * */
        int[] keys;
        BtreeNode[] children;
        boolean leaf;
        int numKeys; // no. of keys

        public BtreeNode(int maxKeys, boolean leaf) {
            this.keys = new int[maxKeys];
            this.children = leaf ? null : new BtreeNode[maxKeys + 1];
            this.leaf = true;
            this.numKeys = 0;
        }

        public void traverse() {
            System.out.println("-------Node--------");
            for (int i =0; i < numKeys; i++ ) {
                System.out.println(keys[i]);
            }

            if (!isLeaf()) {
                for (BtreeNode child: children) {
                    if (child != null) child.traverse();
                }
            }
        }

        public boolean isLeaf() {
            return children == null;
        }

        public int removeMax() {
            for (BtreeNode node = this;;) {
                assert node.numKeys > minKeys;
                if (node.isLeaf()) {
                    return node.removeKeyAndChild(node.numKeys-1, -1);
                } else {
                    node = node.ensureChildRemoval(node.numKeys);
                }
            }
        }

        public int removeMin() {
            for (BtreeNode node = this;;) {
                assert node.numKeys > minKeys;
                if (node.isLeaf()) {
                    return node.removeKeyAndChild(0, -1);
                } else {
                    node = node.ensureChildRemoval(0);
                }
            }
        }

        public BtreeNode ensureChildRemoval(int index) {
            // preliminaries
            assert !this.isLeaf() && 0 <= index && index <= maxKeys;
            BtreeNode child = children[index];
            if(child.numKeys > minKeys) {
                return child;
            }

            BtreeNode left = index >= 1 ? this.children[index - 1] : null;
            BtreeNode right = index >= 1 ? this.children[index + 1]: null;
            boolean internal = !child.isLeaf();
            assert left != null || right != null;

            if (left != null && left.numKeys > minKeys) { // steal right most from the left
                child.insertKeyAndChild(0, this.keys[index - 1],
                        (internal ? 0 : -1), (internal ? left.children[left.numKeys] : null));
                this.keys[index - 1] = left.removeKeyAndChild(left.numKeys - 1, (internal ? left.numKeys: -1));
                return child;
            } else if (right != null && right.numKeys > minKeys) {
                child.insertKeyAndChild(child.numKeys, this.keys[index], (internal ? child.numKeys + 1 : -1), (internal ? right.children[0]: null));
                this.keys[index] = right.removeKeyAndChild(0, (internal ? 0: -1));
                return child;
            } else if (left != null) {
                this.mergeChildren(index-1);
                return  left;
            } else if (right != null) {
                this.mergeChildren(index);
                return child;
            } else
                throw new AssertionError("Impossible condition");

        }

        public void mergeChildren(int index) {
            assert 0 <= index;

            BtreeNode left = children[index];
            BtreeNode right = children[index + 1];

            assert left.numKeys == minKeys && right.numKeys == minKeys;

            if (!left.isLeaf()) {
                System.arraycopy(right.children, 0, left.children, minKeys + 1, minKeys + 1);
            }
            System.arraycopy(right.keys, 0, left.keys, minKeys+1, minKeys+1);
            left.numKeys = left.keys.length;
        }

        public int search(int key) {
            // TODO: replace it with binary search algorithm
            /*
            * Return negated value if key found else return positive value
            * */
            int i = 0;
            for(;i < numKeys; i++) {
                if (key == keys[i]) {
                    System.out.println("duplicate key found! : " + key);
                    i = -1; // not found
                    break;
                } else if (key > keys[i]) {
                    continue;
                } else {
                    break;
                }
            }
            return ~i; //not found
        }

        public void splitChild(int index) {
            BtreeNode left = children[index];

            BtreeNode right = new BtreeNode(maxKeys, true);
            System.out.println("splitting the node");

            if(!left.isLeaf()) {
                System.arraycopy(left.children, minKeys + 1,right.children, 0, minKeys + 1);
                Arrays.fill(left.children, minKeys + 1, left.children.length , 0);
            }

            int middleKey = left.keys[minKeys];
            System.arraycopy(left.keys, minKeys + 1,right.keys, 0, minKeys);
            Arrays.fill(left.keys, minKeys, left.keys.length , 0);
            left.numKeys = minKeys;
            right.numKeys = minKeys;

            insertKeyAndChild(index, middleKey, index+1, right);
        }

        public void insertKeyAndChild(int keyIndex, int key, int childIndex, BtreeNode child) {
            if (isLeaf()) {
                System.out.println("its leaf node!");
                assert childIndex == -1 && child == null;
            } else {
                assert 0 <= childIndex && childIndex <= numKeys + 1 && child != null;
                System.arraycopy(children, childIndex, children, childIndex + 1, numKeys + 1 - childIndex);
                System.out.println("inserting children at :" + childIndex);
                children[childIndex] = child;
            }

            System.out.println("inserting key :" + key + " numKeys: " + numKeys);
            System.arraycopy(keys, keyIndex, keys, keyIndex + 1, numKeys - keyIndex);
            keys[keyIndex] = key;
            numKeys++;
        }

        public int removeKeyAndChild(int keyIndex, int childIndex) {

            if (isLeaf()) {
                assert childIndex == -1;
            } else {
                assert 0 <= childIndex && childIndex <= numKeys;
                assert children[childIndex] != null;
                System.arraycopy(children, childIndex + 1, children, childIndex, numKeys - childIndex);
                children[numKeys] = null;
            }

            int result = keys[keyIndex];
            System.arraycopy(keys, keyIndex + 1, keys, keyIndex, numKeys - keyIndex + 1);
            numKeys -= 1;
            return result;
        }

    }

    //-------------------------------------------

    public void traverse() {
        root.traverse();
    }

    public boolean add(int key) {
        boolean keyInsert = false;
        if (root.numKeys == maxKeys) {
            BtreeNode child = this.root;
            root = new BtreeNode(maxKeys, false);
            root.children[0] = child;
            root.splitChild(0);
        }

        BtreeNode node = root;
        while (true) {
            int index = node.search(key);
            if (index >= 0) {
                keyInsert = false;
                break;
            }
            index = ~index;

            assert index >= 0;
            if (node.isLeaf()) {
                if (node.numKeys == Integer.MAX_VALUE)
                    throw new IllegalStateException("Maximum size reached");
                System.out.println("inserting at index: "+ index + "  key: " + key);
                node.insertKeyAndChild(index, key, -1, null);
                keyInsert = true;
                break;
            } else {
                BtreeNode child = node.children[index];
                if (child.numKeys == maxKeys) {
                    node.splitChild(index);
                    if (key == node.keys[index]) {
                        keyInsert = false;  // Key already exists in tree
                    } else if (key > node.keys[index])
                        child = node.children[index + 1];
                }
                node = child;
            }
        }
        return keyInsert;
    }

    public boolean remove(int key) {
        /* Source: https://medium.com/@vijinimallawaarachchi/all-you-need-to-know-about-deleting-keys-from-b-trees-9090f3334b5c
        *
        * Case 1: when k is in leaf node and X.n >= t-1
        * Case 2: When k is in leaf node and X.n == t-1
        *      Case 2.a: when k is in X and Y (Sibling node) is >= t-1
        *      Case 2.b: when k is in X and Y (Sibling node) is == t-1
        * Case 3: When k is in the node X and X is an internal node (not a leaf)
        *       Case 3.a: When child node y that precedes k (the node which is on the left side of k) and y.n >= t
        *       Case 3.b: When the child node y that precedes k and y.n < t (or y.n == (t-1))
        *       Case 3.c: When the child node y that precedes k and the child node z that follows k y.n == (t-1) AND z.n == (t-1)
        *
        * */
        BtreeNode node = root;
        while(true) {
            int index = node.search(key);
            if (node.isLeaf()) {
                if (index >= 0) {
                    node.removeKeyAndChild(index, -1);
                } else {
                    BtreeNode child = node.children[index];
                    node = child;
                }
            } else { // internal node
                if (index >= 0) {  // Key is stored at current node
                    BtreeNode left = node.children[index];
                    BtreeNode right = node.children[index+1];
                    if (left.numKeys > minKeys) {
                        key = left.removeMax();
                        node.keys[index] = key;
                        return true;
                    } else if (right.numKeys > minKeys) {
                        key = left.removeMin();
                        node.keys[index] = key;
                        return true;
                    } else {
                        node.mergeChildren(index);
                        if (node == root && root.numKeys == 0) {
                            root = root.children[0];  // Decrement tree height
                            assert root != null;
                        }
                        node = left;
                        index = minKeys;  // Index known due to merging; no need to search
                    }
                } else {  // Key might be found in some child
                    BtreeNode child = node.ensureChildRemoval(~index);
                    if (node == root && root.numKeys == 0) {
                        root = root.children[0];  // Decrement tree height
                        assert root != null;
                    }
                    node = child;
                    index = node.search(key);
                }

            }
        }
    }

    public static void main(String[] args) {
        Btree T = new Btree(4);

        T.add(75);
        T.add(60);
        T.add(95);
        T.add(40);
        T.add(105);
        T.add(30);
        T.add(25);
        T.add(20);
        T.add(15);
        T.add(10);
        T.add(105);
        T.traverse();
    }

}

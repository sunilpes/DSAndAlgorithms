package com.sunil.collections.questions.misc;


import java.util.HashMap;

public class LRUCache {
    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if(!hs.containsKey(key)) {
            return -1;
        }
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        moveToTail(current);
        return hs.get(key).value;
    }

    public void put(int key, int value) {
        if( get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) {
            hs.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        hs.put(key, insert);
        moveToTail(insert);
    }

    private void moveToTail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }

    public void printList() {
        Node node = head;
        while(node != null) {
            System.out.println(node.key + " -> " + node.value);
            node = node.next;
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.printList();
        lruCache.put(1, 1);
        lruCache.printList();
        lruCache.put(2, 3);
        lruCache.printList();
        lruCache.put(4, 1);
        lruCache.printList();
        lruCache.get(1);
        lruCache.printList();
        lruCache.get(2);
//        lruCache.printList();
//        lruCache.get(1);
//        lruCache.printList();
//        lruCache.put(4, 4);
//        lruCache.printList();
    }

}

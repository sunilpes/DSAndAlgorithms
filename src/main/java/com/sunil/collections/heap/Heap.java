package com.sunil.collections.heap;

import java.util.Arrays;

import static java.util.Arrays.*;


public abstract class Heap {

    public enum Heapify {
        MAX,
        MIN
    }

    protected int size;
    protected int[] items;
    protected int capacity;

    public Heap() {
        this.capacity = 10;
        this.size = 0;
        this.items = new int[this.capacity];
    }

    protected void insert(int elem) {
        ensureCapacity();

        items[size] = elem;
        size++;

        heapifyUp();
    }

    public int getLeftChildIndex(int pos) {
        return 2 * pos + 1;
    }

    public  int getRightChildIndex(int pos) {
        return 2 * pos + 2;
    }

    public  int getParentIndex(int pos) {
        return (pos - 1)/2;
    }

    public  int leftChild(int index) {
        try {
            return items[getLeftChildIndex(index)];
        } catch (ArrayIndexOutOfBoundsException aioob) {
            return 0;
        }
    }

    public  int rightChild(int index) {
        try {
            return items[getRightChildIndex(index)];
        } catch (ArrayIndexOutOfBoundsException aioob) {
            return 0;
        }
    }

    public int parent(int index) {
        return items[getParentIndex(index)];
    }

    public boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    public boolean hasLeftChildren(int index) {
        return getLeftChildIndex(index) < size;
    }

    public boolean hasRightChildren(int index) {
        return getRightChildIndex(index) < size;
    }

    public void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    public int poll() {
        int value = items[0];
        items[0] = items[size-1];
        items[size-1] = 0;
        size--;
        heapifyDown();
        return value;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity = capacity << 2;
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void print() {
        int index = 0;
        while (index < size) {
            System.out.println(String.format("Parent:  %d children's : (L) %d (R) %d", items[index], leftChild(index), rightChild(index)));
            index++;
        }
    }


    public abstract Heap heapify(int[] data);

    /** Swap values down the Heap. **/
    public abstract void heapifyDown();

    /** Swap values up the Heap. **/
    public abstract void heapifyUp();

}

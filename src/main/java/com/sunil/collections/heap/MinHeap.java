package com.sunil.collections.heap;

public class MinHeap extends Heap {


    public void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index) > items[index]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void heapifyDown() {
        int index = 0;

        while(hasLeftChildren(index)) {
            int smallIndex = getLeftChildIndex(index);

            if (hasRightChildren(index) && rightChild(index) < leftChild(index)) {
                smallIndex = getRightChildIndex(index);
            }
            if (items[smallIndex] < items[index]) {
                swap(index, smallIndex);
            }
            index = smallIndex;
        }
    }

    public static void main(String[] args) {
        Heap minHeap = new MinHeap();
        minHeap.insert(5);
        minHeap.insert(10);
        minHeap.insert(6);
        minHeap.insert(15);
        minHeap.insert(24);
        minHeap.insert(13);

        minHeap.print();

        System.out.println("polling element: " + minHeap.poll());
        minHeap.print();
    }
}

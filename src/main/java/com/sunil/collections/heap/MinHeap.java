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

    public Heap heapify(int[] data) {
        Heap minHeap = new MinHeap();
        for (int index = 0; index < data.length; index++) {
            minHeap.insert(data[index]);
        }
        return minHeap;
    }

    public static void main(String[] args) {
        Heap minHeap = new MinHeap();
        int index = 0;

        int[] data = new int[31];
        for (int i =31; i >= 1; i--) {
            data[index] = i;
            index++;
        }

        Heap heap = minHeap.heapify(data);

        heap.print();

//        System.out.println("polling element: " + minHeap.poll());
  //      minHeap.print();
    }
}

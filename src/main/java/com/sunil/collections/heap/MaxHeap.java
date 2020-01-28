package com.sunil.collections.heap;

import java.lang.reflect.Array;

public class MaxHeap extends Heap {

    public void heapifyUp() {
        int index = size - 1;

        while(hasParent(index)
                &&   parent(index) < items[index]
        ) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDown() {
        int index  = 0;
        while(hasLeftChildren(index)) {
            int childIndex = getLeftChildIndex(index);

            if (hasRightChildren(index) && rightChild(index) > leftChild(index)) {
                childIndex = getRightChildIndex(index);
            }

            if(items[childIndex] > items[index]) {
                swap(childIndex, index);
            }

            index = childIndex;
        }
    }


    public Heap heapify(int[] data) {
        int size = data.length - 1;
        Heap maxHeap = new MaxHeap();
        for (int index = 0; index <= size; index++) {
            maxHeap.insert(data[index]);
        }
        return maxHeap;
    }


    public static void main(String[] args) {
        Heap maxHeap = new MaxHeap();
        maxHeap.insert(5);
        maxHeap.insert(10);
        maxHeap.insert(6);
        maxHeap.insert(15);
        maxHeap.insert(24);
        maxHeap.insert(13);

        maxHeap.print();

        System.out.println("polling element: " + maxHeap.poll());
        maxHeap.print();

        System.out.println("Heapify max array");
        int[] data = new int[]{1,2,3,4,5,6,7,8,9};
        Heap maxHeapify = maxHeap.heapify(data);
        maxHeapify.print();
    }
}

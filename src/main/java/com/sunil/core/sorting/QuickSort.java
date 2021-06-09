package com.sunil.core.sorting;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=COk73cpQbFQ&feature=emb_rel_end&ab_channel=mycodeschool
 */

public class QuickSort {

    private static void swap(int[] unsorted, int src, int dest) {
        int temp = unsorted[src];
        unsorted[src] = unsorted[dest];
        unsorted[dest] = temp;
    }

    public static int partition(int[] unsorted, int start, int end) {
        int pivot = unsorted[end]; // taking right most as pivot
        int pIndex = start;
        for (int i=start;i<=end-1;i++) {
            if(unsorted[i] <= pivot) {
                swap(unsorted, i, pIndex);
                pIndex += 1;
            }
        }
        swap(unsorted, end, pIndex);
        return pIndex;
    }

    public static void sort(int[] unsorted, int start, int end) {
        if (start < end) {
            int pIndex = partition(unsorted, start, end);
            sort(unsorted, start, pIndex-1);
            sort(unsorted, pIndex+1, end);
        }
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{8,6,4,2,5};
        sort(numbers, 0, numbers.length-1);
        System.out.println(String.format("sorted: %s", Arrays.toString(numbers)));
    }
}

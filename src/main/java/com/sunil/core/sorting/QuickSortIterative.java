package com.sunil.core.sorting;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * https://javarevisited.blogspot.com/2016/09/iterative-quicksort-example-in-java-without-recursion.html#axzz5ArdIFI7y
 */

public class QuickSortIterative {

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

    public static void sort(int[] numbers, int start, int end) {
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(start);
        stack.push(end);

        while(!stack.isEmpty()) {
            int last = stack.pop();
            int first = stack.pop();
            if (last > first) {
                int pIndex = partition(numbers, first, last);
                stack.push(first);
                stack.push(pIndex - 1);

                stack.push(pIndex + 1);
                stack.push(end);
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{8,6,4,2,1};
        sort(numbers, 0, numbers.length-1);
        System.out.println(String.format("sorted: %s", Arrays.toString(numbers)));
    }
}

package com.sunil.core.sorting;
import java.util.Arrays;

/**
 * https://javarevisited.blogspot.com/2014/12/insertion-sort-algorithm-in-java-to-array-example.html
 *
 */

public class InsertionSort {

    public static void compareAndSwap(int[] unsorted) {
        for(int j=0; j<unsorted.length;j++) {
            int current = unsorted[j];
            int i = j;
            while(i > 0 && unsorted[i-1] > current) {
                unsorted[i] = unsorted[i-1];
                i--;
            }
            unsorted[i] = current;
        }
    }

    public static void sort(int[] numbers) {
        compareAndSwap(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public static void main(String[] args) {
        sort(new int[] {10,9,8,34,2,1,0});
    }
}

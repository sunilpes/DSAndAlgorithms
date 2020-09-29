package com.sunil.core.sorting;

import java.util.Arrays;

/**
 * https://javarevisited.blogspot.com/2014/08/bubble-sort-algorithm-in-java-with.html
 *
 */

public class BubbleSortImproved {

    static boolean compareAndSwap(int[] numbers) {
        boolean isSwapped = false;

        for(int j = 0; j < numbers.length-1; j++) {
            if(numbers[j] > numbers[j+1]) {
                int temp = numbers[j+1];
                numbers[j+1] = numbers[j];
                numbers[j] = temp;
                isSwapped = true;
            }
        }
        return isSwapped;
    }

    public static void sort(int[] numbers) {
        int noOfPass = -1;
        do {
            noOfPass++;
        } while(compareAndSwap(numbers));

        System.out.println("result: " + Arrays.toString(numbers));
        System.out.println("no of pass: "+ noOfPass);
    }

    public static void main(String[] args) {
        int[] input = new int[] {-1, -90, -1000, 0, 90,10,2,3,80,76,34};
        //int[] input = new int[] {0,1,2,3,4,56,78};
        System.out.println("Input: " + Arrays.toString(input));
        sort(input);
    }
}

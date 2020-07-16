package com.sunil.collections.questions.array;

import java.util.Arrays;

/**
 * No. ways to check if an array os sorted or not
 * 1. check array elements next to each other.
 * 2. Recursively check the elements next to each other.
 */



public class SortedArray {

    enum SortType {
        ASC,
        DESC
    }

    /**
     * 1. check array elements next to each other.
     * @param numbers
     * @param type
     * @return
     */
    public static boolean isSorted(int[] numbers, SortType type) {
        System.out.println(Arrays.toString(numbers));
        switch (type) {
            case ASC:
                for(int i=0;i<numbers.length-1;i++) {
                    if(numbers[i] > numbers[i+1]) {
                        return false;
                    }
                }
                break;
            case DESC:
                for(int i=0;i<numbers.length-1;i++) {
                    if(numbers[i] < numbers[i+1]) {
                        return false;
                    }
                }
                break;
        }

        return true;
    }

    /**
     * 2. check array elements next to each other recursively.
     * @param numbers
     * @return
     */
    public static boolean isSorted(int[] numbers, int length) {
        if (numbers == null || length < 2) {
            return true;
        } else if(numbers[length-2] > numbers[length-1]) {
            return false;
        }
        return isSorted(numbers, length-1);
    }


    public static void main(String[] args) {
        System.out.println(String.format("Ascending order: %b", isSorted(new int[]{3,4,5,6,7}, SortType.ASC)));
        System.out.println(String.format("Descending order: %b",isSorted(new int[]{10,9,8,7,6,5,4}, SortType.DESC)));

        System.out.println(String.format("Ascending order: %b", isSorted(new int[]{3,4,5,6,7}, 5)));
        System.out.println(String.format("Descending order: %b",isSorted(new int[]{10,9,8,7,6,5,4}, 7)));
    }
}

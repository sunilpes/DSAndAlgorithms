package com.sunil.collections.questions.array;

/**
 * Question:
 * How do you find the largest and smallest number in an unsorted integer array?
 *
 */
public class LargestSmallest {

    public static void printLargestSmallest(int[] numbers) {
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        for (int num: numbers) {
            if (num > largest) {
                largest = num;
            } else if (num < smallest) {
                smallest = num;
            }
        }
        System.out.println(String.format(String.format("smallest: %d", smallest)));
        System.out.println(String.format(String.format("largest: %d", largest)));
    }

    public static void main(String[] args) {
        printLargestSmallest(new int[]{-2,-3,-5,0,12,234,789897,232, -110, 567, Integer.MAX_VALUE});
    }
}

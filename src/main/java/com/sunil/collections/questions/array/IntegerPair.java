package com.sunil.collections.questions.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Question:
 * How do you find all pairs of an integer array whose sum is equal to a given number?
 */

public class IntegerPair {

    public static void main(String[] args) {
        printSumPair(getRandomArray(10), 17);
    }

    public static void printSumPair(int[] numbers, int sum) {
        assert sum > 2: "sum should be greater than 2!";
        System.out.println("numbers: " + Arrays.toString(numbers) + " sum: " + sum);

        HashSet<Integer> hs = new HashSet<Integer>();

        for (int num: numbers) {
            int target = sum - num;

            if(!hs.contains(target)) {
                hs.add(num);
            } else {
                System.out.println(String.format("pair: %d, %d for sum: %d", target, num, sum));
            }
        }
    }

    public static int[] getRandomArray(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i<length; i++) {
            numbers[i] = (int) (Math.random() * 50);
        }
        return numbers;
    }
}

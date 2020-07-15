package com.sunil.collections.questions.array;

import java.util.BitSet;

/**
 * Question:
 * How do you find the missing number in a given integer array of 1 to 100?
 *
 */
public class FindMissingNumber {

    public static void main(String[] args) {
        printMissingNumbers(new int[]{1,2,3,5,6}, 6);
        getMissingNumber(new int[]{1,2,4,5,6}, 6);
    }

    public static void printMissingNumbers(int[] numbers, int count) {
        BitSet bs = new BitSet(count);
        for (int i: numbers) {
            // it will set bit(1) from index defined, starting from 0 index
            bs.set(i-1);
        }
        int lastMissingIndex = 0;

        for (int i=0; i<(count-numbers.length); i++) {
            lastMissingIndex = bs.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex);
        }
    }

    public static void getMissingNumber(int[] numbers, int count) {
        int totalCount = count * (count + 1)/2;
        int missingCount = 0;
        for (int number: numbers) {
            missingCount += number;
        }
        System.out.println(totalCount-missingCount);
    }
}

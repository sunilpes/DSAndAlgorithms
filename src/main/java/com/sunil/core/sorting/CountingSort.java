package com.sunil.core.sorting;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=pEJiGC-ObQE&ab_channel=Jenny%27slecturesCS%2FITNET%26JRF
 */

public class CountingSort {

    public static int[] sort(int[] numbers) {
        int[] result = new int[numbers.length];
        int k = Integer.MIN_VALUE;
        int sum = 0;


        for(int number: numbers) {
             if (number > k) {
                 k = number;
             }
        }

        int[] counter = new int[k+1];

        for(int number: numbers) {
            counter[number] += 1;
        }

        for(int i=0;i<counter.length;i++) {
            sum += counter[i];
            counter[i] = sum;
        }

        for(int i=numbers.length-1; i>=0; i--) {
            int num = numbers[i];
            counter[num] -= 1;
            result[counter[num]] = num;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[] {7,2,1,8,2,5,5,10,19})));
    }
}

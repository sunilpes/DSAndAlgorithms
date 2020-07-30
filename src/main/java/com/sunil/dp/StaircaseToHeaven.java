package com.sunil.dp;

import java.util.Arrays;

/**
 *
 * There's a staircase with N steps, and you can climb 1 or 2 steps at a time. Given N, write a function that returns
 * the number of unique ways you can climb the staircase. The order of the steps matters.
 *
 * For example, if N is 4, then there are 5 unique ways:
 *
 * 1, 1, 1, 1
 * 2, 1, 1
 * 1, 2, 1
 * 1, 1, 2
 * 2, 2
 * What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive
 * integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
 * Generalize your function to take in X.
 */
public class StaircaseToHeaven {

    public static int numWays(int n, int[] ways) {
        if (n <= 0) return 1;
        int[] num = new int[n+1];
        num[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int way: ways) {
                if ((i-way) >= 0) {
                    num[i] += num[i-way];
                }
            }
        }
        System.out.println(Arrays.toString(num));
        return num[n];
    }

    public static void main(String[] args) {
        int number  = 5;
        System.out.printf("num ways for %d: %d \n",number,  numWays(number, new int[]{1,3,5}));
        System.out.printf("num ways for %d: %d \n",number,  numWays(number, new int[]{1,2}));
    }
}

package com.sunil.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * For example, if length of the rod is 8 and the values of different pieces are given as following, then the maximum
 * obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 *
 *
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)
 *
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 3   5   8   9  10  17  17  20
 */

public class RodCutting {

    public static int cutRod(int[] price, int rodLength) {
        int[] rod = new int[rodLength];

        for(int i = 0; i < rodLength; i++) {
            rod[i] = Math.max(rod[i], price[i] + rod[rodLength-i-1]);
        }
        System.out.println("Length   : " + Arrays.toString(IntStream.range(1, price.length+1).toArray()));
        System.out.println("Price    : " + Arrays.toString(price));
        return rod[rodLength-1];
    }

    public static void main(String[] args) {
        System.out.println("Max price " + cutRod(new int[]{1,5,8,9,10,17,17,20}, 4));
    }
}

package com.sunil.dp;

/**
 * You are located at top right corner of m * n grid
 * you only move either right or down at any point in time.
 * Your home is located at right-bottom corner of this grid.
 *
 *  In how many unique ways you can reach your home?
 *
 */


public class OnTheWayToHome {

    public static int numWays(int m, int n) {
        if (m < 1 || n < 1) return 0;

        assert (m < Integer.MAX_VALUE && n < Integer.MAX_VALUE): "Integer no. should not be more than equal to MAX_VALUE";

        int[][] num = new int[m][n];

        // base cases:
        // 1. left most column cannot go right, so no. of ways it can reach right-bottom is only 1
        // 2. bottom row cannot go further down, so no. of ways it can reach right-bottom is only 1
        for (int k =0; k <n;k++) {
            num[m-1][k] = 1;
        }

        for (int g =0; g <m;g++) {
            num[g][n-1] = 1;
        }

        for (int i=(m-2); i >= 0; i--) {
            for (int j=(n-2);j >= 0 ; j--) {
                //System.out.println("["+i +","+ j+"]");
                num[i][j] = num[i][j+1] + num[i+1][j];
            }
        }
        return num[0][0];
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        System.out.printf("num of ways for %d * %d grid is: %d", m, n, numWays(m, n));
    }

}

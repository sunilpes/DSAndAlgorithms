package com.sunil.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FibonacciSeries {

    public static int findFibonacciWithSpaceOptimized(int num) {
        if (num <= 0) return 0;
        if (num <= 1) return 1;

        assert num < Integer.MAX_VALUE: "Number should not exceed Integer.MAX_VALUE";
        int a = 0, b = 1, c = 0;

        for(int i = 2; i < num; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }

    public static int findFibonacci(int num) {
        if (num <= 0) return 0;
        if (num <= 1) return 1;

        assert num < Integer.MAX_VALUE: "Number should not exceed Integer.MAX_VALUE";

        int[] result = new int[num];
        result[0] = 0;
        result[1] = 1;
        IntStream
                .range(2, num)
                .forEach((n) -> {
                    result[n] = result[n - 1] + result[n - 2];
                });

        System.out.println(Arrays.toString(result));

        return result[num-1];
    }

    public static void main(String[] args) {
        int number = 10;
        System.out.printf("fibonacci of %d:  %d", number, findFibonacciWithSpaceOptimized(number));
    }

}

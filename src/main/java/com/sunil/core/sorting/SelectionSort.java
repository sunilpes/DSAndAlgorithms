package com.sunil.core.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void sort(int[] numbers) {
        int n = numbers.length;
        for(int j=0; j < n-1;j++) {
            int minIdx = j;
            for(int i = j+1; i < n; i++) {
                if(numbers[i] < numbers[minIdx]) {
                    minIdx = i;
                }
            }
            int temp = numbers[j];
            numbers[j] = numbers[minIdx];
            numbers[minIdx] = temp;
        }
        System.out.println(Arrays.toString(numbers));
    }

    public static void main(String[] args) {
        sort(new int[] {10,9,8,34,2,1,0});
    }
}

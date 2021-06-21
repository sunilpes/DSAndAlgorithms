package com.sunil.core.sorting;

import java.util.Arrays;

public class BubbleSort {

    public void sort(int[] numbers) {
        for(int i = 0; i< numbers.length;i++) {
            for(int j=i+1; j<numbers.length; j++) {
                if(numbers[i] > numbers[j]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(numbers));
    }


    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort();
        bs.sort(new int[]{-1, -90, -1000, 0, 90,10,2,3,80,76,34});
    }
}

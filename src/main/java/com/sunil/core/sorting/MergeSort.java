package com.sunil.core.sorting;

import java.util.Arrays;

public class MergeSort {

    public int[] sort(int[] numbers) {
        int middle = numbers.length/2;
        System.out.println("length: " + numbers.length + " middle : " + middle);
        System.out.println(Arrays.toString(numbers));

        if (numbers.length <= 1) return numbers;

        System.out.println("---sort-left----");
        int[] left = sort(Arrays.copyOfRange(numbers, 0, middle));
        System.out.println("---sort-right----");
        int[] right = sort(Arrays.copyOfRange(numbers, middle, numbers.length));
        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));

        int[] result  = merge(left, right);
        System.out.println("---merge----");
        System.out.println(Arrays.toString(result));
        return result;
    }

    public int[] merge(int[] left, int[] right) {

        if (left.length == 1 && right.length == 1) {
            if(right[0] > left[0]) return new int[]{left[0], right[0]};
            return new int[]{right[0], left[0]};
        }

        int[] merged = new int[left.length+right.length];
        int l = 0, r = 0, p = 0;

        while(l < left.length && r < right.length) {
            if(left[l] > right[r]) {
                merged[p] = right[r];
                r++;
            } else if (left[l] <= right[r]) {
                merged[p] = left[l];
                l++;
            }
            p++;
        }
        if(p < merged.length) {
            if (l == left.length) {
                for (int g = r; g < right.length; g++) {
                    merged[p] = right[g];
                    p++;
                }
            } else {
                for (int g = l; g < left.length; g++) {
                    merged[p] = left[g];
                    p++;
                }
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] numbers = new int[]{-4,0,7,4,9,-5,-1,0,-7,-1};
        System.out.println("----------result----------");
        System.out.println(Arrays.toString(ms.sort(numbers)));
    }

}

package com.sunil.core.strings;

/*
* https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
*
* */

public class FindPermutations {

    public static void permute(String str, int left, int right) {
        if (right==left) {
            System.out.println(str);
        } else {
            for (int i = left; i<=right; i++) {
                str = swap(str, left, i);
                permute(str, left+1, right);
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        String str = "ABC";
        permute(str, 0, str.length()-1);
    }
}

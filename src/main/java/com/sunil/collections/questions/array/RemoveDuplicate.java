package com.sunil.collections.questions.array;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Question:
 * How do you find the duplicate number on a given integer array
 *
 */

public class RemoveDuplicate {

    public static void main(String[] args) {
        int[] a1= new int[]{1,2,2,4,5,6,7,2,8};

        HashSet<Integer> hs = new HashSet<Integer>();
        for(int num: a1) {
            hs.add(num);
        }

        Iterator<Integer> it = hs.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }
}

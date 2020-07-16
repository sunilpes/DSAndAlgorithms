package com.sunil.collections.questions.array;

import java.util.*;

public class RemoveDuplicateArrayList {

    public static void removeDuplicate(List<Integer> numbers) {
        Set<Integer> lhs = new LinkedHashSet<Integer>(numbers);
        ArrayList<Integer> result = new ArrayList<>(lhs);
        for (Integer integer : result) {
            System.out.print(integer+",");
        }
        System.out.println("\n-------------");
    }

    public static void main(String[] args) {

        removeDuplicate(Arrays.asList(1,2,3,4,5,6,6));
        removeDuplicate(Arrays.asList(4,6,7,8,1,1,5,5));
        removeDuplicate(Arrays.asList(-1,-1,4,6,7,8,4,-1));
    }
}

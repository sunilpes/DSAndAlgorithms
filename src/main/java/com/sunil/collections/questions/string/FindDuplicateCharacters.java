package com.sunil.collections.questions.string;

import java.util.HashMap;
import java.util.Map;

public class FindDuplicateCharacters {

    public static void main(String[] args) {
        String subject = "INDIA AMERICA";

        char[] subject_chars = subject.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (char key : subject_chars) {
            if (map.containsKey(key)) {
                int count = map.get(key);
                map.put(key, ++count);
            } else {
                map.put(key, 1);
            }
        }

        for(Map.Entry<Character, Integer> entrySet: map.entrySet()) {
            if (entrySet.getValue() > 1) {
                System.out.printf("key %s, value %s \n", entrySet.getKey(), entrySet.getValue());
            }
        }
    }
}

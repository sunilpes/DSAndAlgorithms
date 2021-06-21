package com.sunil.collections.questions.string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        String[] string = s.split("");
        int longest = 0;
        for (int j = 0; j < string.length; j++) {
            int count = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = j; i < string.length; i++) {
                if(map.get(string[i]) == null) {
                    map.put(string[i], 1);
                    count++;
                } else break;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.lengthOfLongestSubstring("abcdbac"));
    }
}

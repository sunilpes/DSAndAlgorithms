package com.sunil.collections.questions.string;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakRecur(s, new HashSet<>(wordDict), 0);
    }

    private boolean wordBreakRecur(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        System.out.println("recur - > " + start);
        for (int end = start + 1; end <= s.length(); end++) {
            System.out.println(s.substring(start, end));
            System.out.println("start: " + start + " end: " + end);
            if (wordDict.contains(s.substring(start, end)) && wordBreakRecur(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        boolean result = wb.wordBreak("catsandog", Arrays.asList("cat", "cats", "sand", "dog"));
        System.out.println(result);
    }
}

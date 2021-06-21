package com.sunil.collections.questions.string;

public class TypedOutString {

        public boolean backspaceCompare(String s, String t) {
            return buildString(s).equals(buildString(t));
        }

        public String buildString(String s) {
            StringBuilder finalString = new StringBuilder();
            String[] string = s.split("");
            int deleteCount = 0;
            for(int index = string.length - 1; index >= 0; index--) {
                if (string[index].equals("#")) deleteCount++;
                else if (deleteCount == 0) finalString.append(string[index]);
                else deleteCount--;
            }
            return finalString.toString();
        }

    public static void main(String[] args) {
        TypedOutString tos = new TypedOutString();
        System.out.println(tos.backspaceCompare("bxj##tw", "bxj###tw"));
    }
}

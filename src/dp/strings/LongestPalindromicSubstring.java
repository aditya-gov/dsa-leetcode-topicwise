package dp.strings;

import static dp.lcs.LongestCommonSubsequence.lcs;

public class LongestPalindromicSubstring {

    public int lengthLongestPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        String revStr = sb.reverse().toString();
        int length = lcs(str, revStr);
        return length;
    }

    public static void main(String[] args) {
        String s = "babad";
        LongestPalindromicSubstring solver = new LongestPalindromicSubstring();
        System.out.printf("Longest Palindromic Substring: " + solver.lengthLongestPalindrome(s));
    }

}

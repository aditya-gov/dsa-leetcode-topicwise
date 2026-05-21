package dp.lcs;

/**
 * Hint: LCS between the given string & the reversed form of it
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromicSubsequence(String s) {
        int m = s.length();
        StringBuilder sb = new StringBuilder(s);
        String t = sb.reverse().toString();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "bbabcbcab";
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        System.out.println("Longest Palindromic Subsequence: " + lps.longestPalindromicSubsequence(s));
    }
}

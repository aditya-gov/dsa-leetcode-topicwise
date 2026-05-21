package dp.strings;

import java.util.Arrays;

public class DistinctSubsequence {

    public static int distinctSubsequence(String s) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = (2 * dp[i - 1]);
            int index = s.charAt(i - 1) - 'a';
            if (last[index] != -1) {
                dp[i] = (dp[i] - dp[last[index]]);
            }
            last[index] = i - 1;
        }
        return dp[n] - 1;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(distinctSubsequence(s));
    }

}

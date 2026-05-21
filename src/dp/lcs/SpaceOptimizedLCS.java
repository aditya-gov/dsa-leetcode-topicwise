package dp.lcs;

public class SpaceOptimizedLCS {

    public static int spaceOptimisedLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            // prev stores the value from the previous row (i-1) and previous column (j -1)
            int prev = dp[0];
            for (int j = 1; j <= n; j++) {
                // temp temporarily stores the current dp[j] before it gets updated
                int temp = dp[j];
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // If characters match, add 1 to the value from the previous row and previous column
                    dp[j] = 1 + prev;
                } else {
                    // Otherwise, take the maximum of the left and top values
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                // Update prev for the next iteration
                prev = temp;
            }
        }
        return dp[n];
    }

}

package dp;

public class CoinChangeSpaceOptimized {

    public static int coinChangeSpaceOptimized(int[] coins, int sum) {
        int n = coins.length;
        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int i=0; i<n; i++) {
            for (int j = coins[i]; j <= sum; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        int[] coins = {1,2,3};
        int sum = 5;
        System.out.println(coinChangeSpaceOptimized(coins, sum));
    }

}

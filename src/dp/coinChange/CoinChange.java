package dp.coinChange;

public class CoinChange {

    public static int minCoins(int[] coins, int value) {
        int n = coins.length;
        int[] dp = new int[value + 1];
        dp[0] = 0;

        for (int i = 1; i <= value; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != -1) {
                    int subResult = dp[i - coins[j]];
                    min = Math.min(min, subResult);
                }
            }
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        }

        return dp[value];
    }

    public static void main(String[] args) {
        int[] coins = {9,6,5,1};
        int value = 11;
        System.out.println(minCoins(coins, value));
    }

}

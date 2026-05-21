package dp.knapsack;

public class ZeroOneKnapsack {

    public static int knapsack(int capacity, int[] profit, int[] weight) {
        int n = profit.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int c = 0; c <= capacity; c++) {
                if (i == 0 || c == 0) {
                    dp[i][c] = 0;
                } else if (weight[i - 1] <= c) {
                    dp[i][c] = Math.max(profit[i - 1] + dp[i - 1][c - weight[i - 1]], dp[i -1][c]);
                } else {
                    dp[i][c] = dp[i - 1][c];
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] profit1 = new int[] {60, 100, 120};
        int[] weight1 = new int[] {10, 20, 30};
        int capacity1 = 50;
        System.out.println("Test Case 1: " + knapsack(capacity1, profit1, weight1));
        // Test Case 2
        int[] profit2 = new int[] {10, 15, 40};
        int[] weight2 = new int[] {1, 2, 3};
        int capacity2 = 6;
        System.out.println("Test Case 2: " + knapsack(capacity2, profit2, weight2));
    }

}

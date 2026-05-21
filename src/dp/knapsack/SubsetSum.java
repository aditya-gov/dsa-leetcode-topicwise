package dp.knapsack;

public class SubsetSum {

    public int countSubsets(int[] arr, int targetSum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][targetSum + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= targetSum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= arr[i - 1]) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 3 };
        int target = 6;
        SubsetSum subsetSum = new SubsetSum();
        System.out.printf("Count of subsets: " + subsetSum.countSubsets(arr, target));
    }

}

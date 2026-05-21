package dp;

/**
 * Given an array of positive numbers, find the maximum sum of a
 * subsequence such that no two numbers in the subsequence should be adjacent in the array.
 */
public class MaxSumTwoNonContiguousSubsequence {

    public static int maxSum(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return Math.max(arr[0], arr[1]);
        }

        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {5, 5, 10, 100, 10, 5};
        System.out.println(maxSum(arr));
    }

}

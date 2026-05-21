package dp;

public class MaxSumNonContiguousSubsequence {

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

        int[] dp = new int[n + 2];
        dp[0] = 0;
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[n + 1];
    }

    public static void main(String[] args) {
        int[] arr = {12, 9, 7, 33};
        System.out.println(maxSum(arr));
    }

}

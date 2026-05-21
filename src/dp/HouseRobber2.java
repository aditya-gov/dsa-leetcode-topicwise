package dp;

public class HouseRobber2 {

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        return Math.max(robUtil(nums, 0, n - 2), robUtil(nums, 1, n - 1));
    }

    private static int robUtil(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        int n = end - start + 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[start];
        for (int i = 2; i <= n; i++) {
            // nums[start + i - 1] adjusts the index to the correct house in the subset
            dp[i] = Math.max(dp[i - 2] + nums[start + i - 1], dp[i - 1]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(rob(nums));
    }
}

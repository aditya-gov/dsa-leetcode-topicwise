package arrays;

import java.util.Arrays;

/**
 * 1498. Number of Subsequences That Satisfy the Given Sum Condition
 * Return the number of non-empty subsequences of nums such that the sum of the minimum
 * and maximum element on it is less or equal to target.
 * Since the answer may be too large, return it modulo 109 + 7.
 */
public class NoOfSubsequences {

    public int numSubsequences(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int res = 0;
        int[] exp = new int[n];
        exp[0] = 1;
        for (int i = 1; i < n; i++) {
            exp[i] = (exp[i - 1] * 2) % 1000000007;
        }
        while (l <= r) {
            if (nums[l] + nums[r] <= target) {
                res = (res + exp[r - l]) % 1000000007;
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NoOfSubsequences noOfSubsequences = new NoOfSubsequences();
        int[] nums = {3, 5, 6, 7};
        int target = 9;
        System.out.println("No of subsequences: " + noOfSubsequences.numSubsequences(nums, target));
    }

}

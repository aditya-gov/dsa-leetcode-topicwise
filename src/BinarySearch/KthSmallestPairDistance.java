package BinarySearch;

import java.util.Arrays;

public class KthSmallestPairDistance {

    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = nums[n - 1] - nums[0];

        while (low < high) {
            int mid = low + (high - low) / 2;
            // If the number of pairs with distance <= mid is at least k, the k-th smallest distance must be <= mid.
            if (countPairs(nums, mid) >= k) {
                high = mid;
            } else {
                // Otherwise, the k-th smallest distance is strictly greater than mid.
                low = mid + 1;
            }
        }
        return low;
    }

    private static int countPairs(int[] nums, int target) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // If the distance between nums[right] and nums[left] is greater than target,
            // we shrink the window from the left until the distance is <= target.
            while (nums[right] - nums[left] > target) {
                left++;
            }
            // All pairs formed by (nums[i], nums[right]) where left <= i < right are valid pairs with a distance <= target.
            count += (right - left);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1};
        int k = 1;
        System.out.println(smallestDistancePair(nums, k));
    }

}

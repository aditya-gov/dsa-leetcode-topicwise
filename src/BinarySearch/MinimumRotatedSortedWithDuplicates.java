package BinarySearch;

public class MinimumRotatedSortedWithDuplicates {

    public static int findMinimum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else { // nums[mid] == nums[high] is the duplicate case
                high--;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2,0,1};
        System.out.println(findMinimum(nums));
    }

}

package arrays;

public class MinSizeSubArraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0, sum = 0;
        int res = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];
            if (sum < target) {
                j++;
            } else if (sum == target) {
                res = Math.min(res, j - i + 1);
                j++;
            } else {
                while (sum > target) {
                    res = Math.min(res, j - i + 1);
                    sum -= nums[i];
                    i++;
                }
                if (sum == target) {
                    res = Math.min(res, j - i + 1);
                }
                j++;
            }
        }
        if (res == Integer.MAX_VALUE) {
            return 0;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen(target, nums));
    }

}

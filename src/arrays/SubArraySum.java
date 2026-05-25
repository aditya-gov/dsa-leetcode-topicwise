package arrays;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    public static int subArraySumK(int[] nums, int k) {
        int sum = 0, res = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        for (int num : nums) {
            sum += num;
            if (sum == k) {
                res++;
            }
            if (preSum.containsKey(sum - k)) {
                res += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {10, 2, -2, -20, 10};
        int k = -10;
        System.out.println(subArraySumK(arr, k));
    }

}

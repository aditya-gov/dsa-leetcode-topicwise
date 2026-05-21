package hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/">...</a>
 */
public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            hs.add(num);
        }
        for (int num : nums) {
            // If current element is the starting element of a sequence
            if (hs.contains(num) && !hs.contains(num - 1)) {
                // Then check for next elements in the sequence
                int curr = num, count = 0;
                while (hs.contains(curr)) {
                    // Remove this number to avoid recomputation
                    hs.remove(curr);
                    curr++;
                    count++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

}

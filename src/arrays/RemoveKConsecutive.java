package arrays;

import java.util.Stack;

public class RemoveKConsecutive {

    public static int[] removeKConsecutive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            stack.push(nums[i]);
            int count = 0;
            while (!stack.isEmpty() && stack.peek() == nums[i]) {
                count++;
                stack.pop();
            }
            if (count == k) {
                continue;
            } else {
                while (count > 0) {
                    stack.push(nums[i]);
                    count--;
                }
            }
        }
        int[] res = new int[stack.size()];
        int j = stack.size();
        while (!stack.isEmpty()) {
            res[--j] = stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 3, 3, 1, 2, 5, 4, 2};
        int k = 3;
        int[] res = removeKConsecutive(nums, k);
        for (int a : res) {
            System.out.print(a + " ");
        }
    }
}

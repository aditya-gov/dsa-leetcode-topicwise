package stack;

import java.util.Stack;

/**
 * Application of a monotonic stack
 */
public class NumberOfPeopleVisible {

    public static int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) { // next greater
                res[stack.pop()]++;
            }
            // handles the case for left next greater element
            if (!stack.isEmpty()) {
                res[stack.peek()]++;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {10,6,8,5,11,9};
        int[] res = NumberOfPeopleVisible.canSeePersonsCount(heights);
        for (int ele : res) {
            System.out.print(ele + " ");
        }
    }
}

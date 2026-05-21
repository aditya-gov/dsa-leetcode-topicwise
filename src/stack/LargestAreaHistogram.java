package stack;

import java.util.Stack;

public class LargestAreaHistogram {

    public static int maxArea(int[] arr) {
        int n = arr.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int top, curr;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) { // previous smaller marking the left boundary.
                // The popped item is to be considered as the smallest element of the Histogram
                top = stack.pop();
                curr = arr[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                res = Math.max(res, curr);
            }
            stack.push(i);
        }
        // For the remaining items in the stack, next smaller does not exist. Previous smaller is
        // the item just below in the stack.
        while (!stack.isEmpty()) {
            top = stack.pop();
            curr = arr[top] * (stack.isEmpty() ? n : n - stack.peek() - 1);
            res = Math.max(res, curr);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {6, 2, 5, 4, 1, 5, 6};
        System.out.println(maxArea(arr));
    }

}

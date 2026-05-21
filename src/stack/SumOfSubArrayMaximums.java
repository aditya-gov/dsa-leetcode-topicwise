package stack;

import java.util.Stack;

/**
 * To achieve this, we find for each element the number of subarrays where it remains the maximum by computing its left
 * boundary (how many contiguous elements to the left it dominates) and right boundary (how many contiguous elements to
 * the right it dominates). This is done using a monotonic stack, which efficiently finds the nearest greater elements
 * to the left and right in O(n) time.
 * The contribution of each element to the total sum is then given by multiplying its value with the number of subarrays
 * in which it is the maximum (left[i] * right[i]), ensuring an optimal and direct computation of the result without
 * generating all subarrays explicitly.
 */
public class SumOfSubArrayMaximums {

    public static int sumSubarrayMax(int[] arr) {
        int n = arr.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n]; // left[] stores the distance to the Previous Greater Element (PGE)
        int[] right = new int[n]; // right[] stores the distance to the Next Greater Element (NGE)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek(); // Previous Greater Element (PGE)
            stack.push(i);
        }
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i; // Next Greater Element (NGE)
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res += (left[i] * right[i] * arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2};
        System.out.println(SumOfSubArrayMaximums.sumSubarrayMax(arr));
    }
}

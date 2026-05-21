package stack;

import java.util.Stack;

public class SumOfSubArrayMinimums {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n]; // left[] stores the distance to the Previous Smaller Element (PSE)
        int[] right = new int[n]; // right[] stores the distance to the Next Smaller Element (NSE)
        int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek(); // Previous Smaller Element (PSE)
            stack.push(i);
        }
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i; // Next Smaller Element (NSE)
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            long contribution = ((long) left[i] * right[i]) % MOD;
            contribution = (contribution * arr[i]) % MOD;
            res = (res + contribution) % MOD;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        int[] arr = {11,81,94,43,3};
        System.out.println(SumOfSubArrayMinimums.sumSubarrayMins(arr));
    }

}

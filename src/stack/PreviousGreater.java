package stack;

import java.util.Arrays;
import java.util.Stack;

public class PreviousGreater {

    public static int[] printPreviousGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {20, 30, 10, 5, 15};
        System.out.println(Arrays.toString(printPreviousGreater(arr1)));
        int[] arr2 = {10, 4, 2, 20, 40, 12};
        System.out.println(Arrays.toString(printPreviousGreater(arr2)));
    }
}

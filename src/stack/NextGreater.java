package stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreater {

    public static int[] printNextGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 15, 10, 8, 6, 12, 9, 18};
        System.out.println(Arrays.toString(printNextGreater(arr1)));
        int[] arr2 = { 6, 8, 0, 1, 3 };
        System.out.println(Arrays.toString(printNextGreater(arr2)));
    }
}

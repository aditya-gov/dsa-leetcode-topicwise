package stack;

import java.util.Stack;

public class StockSpan {

    public static void findStockSpan(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        System.out.print(1 + " ");

        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            int span = stack.isEmpty() ? i + 1 : i - stack.peek();
            System.out.print(span + " ");
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {60, 10, 20, 15, 35, 50};
        findStockSpan(arr);
    }

}

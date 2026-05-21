package stack;

import java.util.Stack;

public class MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] arr = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    arr[j]++;
                } else {
                    arr[j] = 0;
                }
            }
        }
        res = Math.max(res, maxArea(arr));
        return res;
    }

    private static int maxArea(int[] arr) {
//        for (int a : arr) {
//            System.out.print(a + " ");
//        }
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int top, width;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                top = stack.pop();
                width = (stack.isEmpty() ? i : i - stack.peek() - 1);
                res = Math.max(res, arr[top] * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            top = stack.pop();
            width = (stack.isEmpty() ? n : n - stack.peek() - 1);
            res = Math.max(res, arr[top] * width);
        }
        return res;
    }


    public static void main(String[] args) {
        //char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix = {
                {'0', '1', '1', '0'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '0', '0'}
        };
        System.out.println(maximalRectangle(matrix));
    }
}

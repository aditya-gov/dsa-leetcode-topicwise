package stack;

import java.util.Stack;

/**
 * mat[i][j] == 1 means person i knows person j, and mat[i][j] == 0 means person i does not know person j
 * A celebrity is defined as someone who:
 * 1. Is known by everyone else
 * 2. Does not know anyone (except themselves)
 */
public class CelebrityProblem {

    public static int findCelebrity(int[][] matrix) {
        int n = matrix.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            // if a knows b
            if (matrix[a][b] != 0) {
                stack.push(b);
            } else {
                stack.push(a);
            }
        }
        // potential candidate of celebrity
        int c = stack.pop();
        // check if c is actually a celebrity
        for (int i = 0; i < n; i++) {
            if (i == c) {
                continue;
            }
            // if c doesn't know any person or any person doesn't know c
            // matrix[c][i] == 1 --> c knows any person
            // matrix[i][c] == 1 --> any person know c
            if (matrix[c][i] != 0 || matrix[i][c] == 0) {
                return -1;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 1, 0 },
                            { 0, 1, 0 },
                            { 0, 1, 1 } };
        System.out.println(findCelebrity(matrix));
    }

}

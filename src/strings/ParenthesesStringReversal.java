package strings;

import java.util.Stack;

public class ParenthesesStringReversal {

    public static String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();
        // First pass: Pair up the matching parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int openIdx = stack.pop();
                pair[i] = openIdx;
                pair[openIdx] = i;
            }
        }
        // Second pass: Traverse and build the result
        StringBuilder res = new StringBuilder();
        int dir = 1; // 1 mean moving right, -1 means moving left
        for (int i = 0; i < n; i += dir) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i]; // Teleport to the matching bracket
                dir = -dir; // Reverse the direction
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "(abcd)";
        System.out.println(reverseParentheses(s));
    }
}

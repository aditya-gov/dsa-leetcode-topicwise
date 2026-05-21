package arrays;

import java.util.Stack;

public class StringTransformation {

    public static String transform(String S) {
        Stack<Character> stack = new Stack<>();
        for (char ch : S.toCharArray()) {
            if (!stack.isEmpty()) {
                char top = stack.peek();
                if ((top == 'A' && ch == 'B') || (top == 'B' && ch == 'A')
                    || (top == 'C' && ch == 'D') || (top == 'D' && ch == 'C')) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String str = "ACBDACBD";
        System.out.println(transform(str));
    }

}

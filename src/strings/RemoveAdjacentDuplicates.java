package strings;

import java.util.Stack;

public class RemoveAdjacentDuplicates {

    public static String removeDuplicates(String s) {
        // Approach 1
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == ch) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();

        // Approach 2
        /*Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();*/
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(RemoveAdjacentDuplicates.removeDuplicates(s));
    }

}

package stack;

import java.util.Stack;

public class TrappingRainWater {

    public static int trapRainWater(int[] height) {
        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) { // Previous Greater
                int popHeight = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int dist = i - stack.peek() - 1;
                // minimum of two heights (next and prev greater)
                int minHeight = Math.min(height[stack.peek()], height[i]);
                // amount of water (height of the water layer)
                minHeight -= popHeight;
                res += dist * minHeight;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trapRainWater(height));
    }
}

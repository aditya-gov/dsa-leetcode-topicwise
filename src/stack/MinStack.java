package stack;

import java.util.Stack;

public class MinStack {

    Stack<Integer> minStack;
    Stack<Integer> auxStack;

    MinStack() {
        minStack = new Stack<>();
        auxStack = new Stack<>();
    }

    void push(int x) {
        if (minStack.isEmpty()) {
            minStack.push(x);
            auxStack.push(x);
            return;
        }
        minStack.push(x);
        if (auxStack.peek() >= minStack.peek()) {
            auxStack.push(x);
        }
    }

    void pop() {
        if (auxStack.peek() == minStack.peek()) {
            auxStack.pop();
        }
        minStack.pop();
    }

    int top() {
        return minStack.peek();
    }

    int getMin() {
        return auxStack.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(5);
        stack.push(8);
        stack.push(6);
        System.out.println("Top of the stack: " + stack.top());
        stack.pop();
        System.out.println("Minimum Element from the stack: " + stack.getMin());
    }
}

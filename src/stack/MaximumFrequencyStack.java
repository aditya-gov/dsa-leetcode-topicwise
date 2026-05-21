package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {

    private final Map<Integer, Integer> freqMap;
    private final Map<Integer, Stack<Integer>> setMap;
    private int maxFreq = 0;

    public MaximumFrequencyStack() {
        freqMap = new HashMap<>();
        setMap = new HashMap<>();
    }

    public void push(int x) {
        int freq = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, freq);
        if (freq > maxFreq) {
            maxFreq = freq;
        }
        setMap.computeIfAbsent(freq, z -> new Stack<>()).push(x);
    }

    public int pop() {
        int top = setMap.get(maxFreq).pop();
        freqMap.put(top, freqMap.get(top) - 1);
        if (setMap.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return top;
    }

    public static void main(String[] args) {
        MaximumFrequencyStack maximumFrequencyStack = new MaximumFrequencyStack();
        maximumFrequencyStack.push(4);
        maximumFrequencyStack.push(6);
        maximumFrequencyStack.push(7);
        maximumFrequencyStack.push(6);
        maximumFrequencyStack.push(8);
        System.out.println(maximumFrequencyStack.pop());
        System.out.println(maximumFrequencyStack.pop());
    }

}

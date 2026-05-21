package queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaximum {

    public static List<Integer> maxOfSubArrays(int[] arr, int k) {
        int n = arr.length;
        List<Integer> res = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && arr[dq.peekFirst()] <= arr[i]) { // remove previous smaller
                dq.pollLast();
            }
            dq.addLast(i);
        }
        for (int i = k; i < n; i++) {
            res.add(arr[dq.peekFirst()]);
            // remove elements out of the window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) { // remove previous smaller from back of the deque
                dq.pollLast();
            }
            dq.addLast(i);
        }
        res.add(arr[dq.peekFirst()]);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 1, 7, 3};
        int k = 3;
        System.out.println(SlidingWindowMaximum.maxOfSubArrays(arr, k));
    }

}

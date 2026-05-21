package queue;

import java.util.Deque;
import java.util.LinkedList;

public class SumMinMaxSubArrays {

    public static int sumMinMaxKSubArray(int[] arr, int k) {
        int n = arr.length;
        int res = 0;
        Deque<Integer> maxDq = new LinkedList<>(); // max at front, min at rear
        Deque<Integer> minDq = new LinkedList<>(); // min at front, max at rear

        for (int i = 0; i < k; i++) {
            // remove all previous greater
            removePreviousGreater(arr, minDq, i);
            // remove all previous smaller
            removePreviousSmaller(arr, maxDq, i);
            minDq.addLast(i);
            maxDq.addLast(i);
        }
        for (int i = k; i < n; i++) {
            res += arr[minDq.peekFirst()] + arr[maxDq.peekFirst()];
            // remove elements out of the window
            while (!minDq.isEmpty() && minDq.peekFirst() <= i - k) {
                minDq.removeFirst();
            }
            while (!maxDq.isEmpty() && maxDq.peekFirst() <= i - k) {
                maxDq.removeFirst();
            }
            // remove all previous greater
            removePreviousGreater(arr, minDq, i);
            // remove all previous smaller
            removePreviousSmaller(arr, maxDq, i);
            minDq.addLast(i);
            maxDq.addLast(i);
        }
        res += arr[minDq.peekFirst()] + arr[maxDq.peekFirst()];
        return res;
    }

    private static void removePreviousGreater(int[] arr, Deque<Integer> minDq, int x) {
        while (!minDq.isEmpty() && arr[minDq.peekLast()] >= arr[x]) { // previous greater
            minDq.removeLast();
        }
    }

    private static void removePreviousSmaller(int[] arr, Deque<Integer> maxDq, int x) {
        while (!maxDq.isEmpty() && arr[maxDq.peekLast()] <= arr[x]) { // previous smaller
            maxDq.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, -1, 7, -3, -1, -2} ;
        int k = 4;
        System.out.println(SumMinMaxSubArrays.sumMinMaxKSubArray(arr, k));
    }
}

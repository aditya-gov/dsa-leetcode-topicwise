package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MedianInStream {

    private PriorityQueue<Integer> leftMaxHeap;
    private PriorityQueue<Integer> rightMinHeap;

    public MedianInStream() {
        // Max heap to store the smaller half of numbers
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Min heap to store the greater half of numbers
        rightMinHeap = new PriorityQueue<>();
    }

    public List<Double> findMedian(int[] nums) {
        List<Double> res = new ArrayList<>();
        for (int num : nums) {
            leftMaxHeap.offer(num);
            int temp = leftMaxHeap.poll();
            rightMinHeap.offer(temp);
            if (rightMinHeap.size() > leftMaxHeap.size()) {
                temp = rightMinHeap.poll();
                leftMaxHeap.offer(temp);
            }
            double median;
            if (leftMaxHeap.size() != rightMinHeap.size()) {
                median = leftMaxHeap.peek();
            } else {
                median = (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
            }
            res.add(median);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 15, 1, 3, 2, 8};
        MedianInStream medianInStream = new MedianInStream();
        List<Double> res = medianInStream.findMedian(arr);
        for (Double re : res) {
            System.out.printf(" %.2f", re);
        }
    }

}

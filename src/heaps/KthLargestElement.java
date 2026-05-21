package heaps;

import java.util.PriorityQueue;

public class KthLargestElement {

    public int kthLargest(int[] nums, int k) {
        if (k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        KthLargestElement kthLargestElement = new KthLargestElement();
        System.out.println("Kth Largest Element: " + kthLargestElement.kthLargest(nums, k));
    }

}

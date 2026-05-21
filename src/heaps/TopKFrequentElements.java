package heaps;

import java.util.*;

public class TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        /*PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b)
                -> Objects.equals(a.getValue(), b.getValue()) ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> en : hm.entrySet()) {
            pq.offer(en);
        }*/
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
        pq.addAll(hm.keySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,1,2,3,1,3,2};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

}

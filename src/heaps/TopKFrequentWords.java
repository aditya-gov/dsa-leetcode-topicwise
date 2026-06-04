package heaps;

import java.util.*;

public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> hm = new HashMap<>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        // First approach
        /*PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k,
                (a, b) -> Objects.equals(a.getValue(), b.getValue())
                        ? a.getKey().compareTo(b.getKey()) : Integer.compare(b.getValue(), a.getValue()));*/

        // Second approach (Comparator way)
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k,
                Comparator.<Map.Entry<String, Integer>> comparingInt(Map.Entry::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey));
        for (Map.Entry<String, Integer> en : hm.entrySet()) {
            pq.offer(en);
        }
        List<String> res = new ArrayList<>();
        while (res.size() < k) {
            res.add(pq.poll().getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        String[] words = {"i","love","leetcode","i","love","coding"};
        int k = 3;
        System.out.println("Top K Frequent Words: " + topKFrequentWords.topKFrequent(words, k));
    }

}

package hashing;

import java.util.*;

public class ShortestWordDistance2 {

    private final Map<String, List<Integer>> wordMap;

    public ShortestWordDistance2(List<String> words) {
        wordMap = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            wordMap.put(word, new ArrayList<>());
            wordMap.get(word).add(i);
        }
    }

    public int shortestDistance(String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;
        List<Integer> idxList1 = wordMap.get(word1);
        List<Integer> idxList2 = wordMap.get(word2);
        int i = 0, j = 0;
        while (i < idxList1.size() && j < idxList2.size()) {
            int idx1 = idxList1.get(i);
            int idx2 = idxList2.get(j);
            minDistance = Math.min(minDistance, Math.abs(idx1 - idx2));
            if (idx1 < idx2) {
                i++;
            } else {
                j++;
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "geek", "gfg", "coding", "geek");
        ShortestWordDistance2 shortestWordDistance2 = new ShortestWordDistance2(words);
        System.out.println(shortestWordDistance2.shortestDistance("coding", "hello"));
    }

}

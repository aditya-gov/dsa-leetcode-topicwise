package graph.bfs;

import java.util.*;

public class WordLadder2 {

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) {
            return res;
        }
        Map<String, Integer> distanceMap = new HashMap<>();
        Map<String, List<String>> adjGraph = new HashMap<>();
        bfs(beginWord, endWord, dict, distanceMap, adjGraph);
        if (distanceMap.containsKey(endWord)) {
            List<String> currPath = new ArrayList<>();
            currPath.add(beginWord);
            dfs(beginWord, endWord, adjGraph, currPath, res);
        }
        return res;
    }

    private static void bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> distanceMap, Map<String, List<String>> adjGraph) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        distanceMap.put(beginWord, 0);
        boolean reachedEnd = false;

        while (!q.isEmpty() && !reachedEnd) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String currWord = q.poll();
                int currDistance = distanceMap.get(currWord);
                char[] wordChars = currWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }
                        wordChars[j] = c;
                        String nextWord = String.valueOf(wordChars);
                        // if the mutated word is in the dictionary, it's a valid next step
                        if (dict.contains(nextWord)) {
                            if (!distanceMap.containsKey(nextWord)) {
                                distanceMap.put(nextWord, currDistance + 1);
                                q.add(nextWord);
                            }
                            if (distanceMap.get(nextWord) == currDistance + 1) {
                                adjGraph.computeIfAbsent(currWord, k -> new ArrayList<>()).add(nextWord);
                            }
                            if (nextWord.equals(endWord)) {
                                reachedEnd = true;
                            }
                        }
                    }
                    // Revert back to the original character for the next position's mutations
                    wordChars[j] = originalChar;
                }
            }
        }
    }

    private static void dfs(String currWord, String endWord, Map<String, List<String>> adjGraph, List<String> currPath, List<List<String>> res) {
        // Base case
        if (currWord.equals(endWord)) {
            res.add(new ArrayList<>(currPath));
        }
        // If the current word has no valid outgoing shortest-path edges, then turn back
        if (!adjGraph.containsKey(currWord)) {
            return;
        }
        // Backtracking exploration
        for (String nextWord : adjGraph.get(currWord)) {
            currPath.add(nextWord);
            dfs(nextWord, endWord, adjGraph, currPath, res);
            currPath.remove(currPath.size() - 1);
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(findLadders(beginWord, endWord, wordList));
    }
}

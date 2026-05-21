package graph.bfs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/word-ladder/">...</a>
 */
public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String currWord = q.poll();
                if (currWord.contains(endWord)) {
                    return level;
                }
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
                            q.add(nextWord);
                            // Remove from the dictionary to mark it as visited
                            dict.remove(nextWord);
                        }
                    }
                    // Revert back to the original character for the next position's mutations
                    wordChars[j] = originalChar;
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }
}

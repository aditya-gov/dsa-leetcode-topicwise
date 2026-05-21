package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak2 {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        if (s.length() > 100) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        wordBreakUtil(s, wordDict, res, new StringBuilder());
        return res;
    }

    private static void wordBreakUtil(String s, List<String> wordDict, List<String> res, StringBuilder subList) {
        if (!subList.isEmpty()) {
            subList.append(" ");
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                StringBuilder sb = new StringBuilder(subList);
                sb.append(word);
                if (s.equals(word)) {
                    res.add(sb.toString());
                } else {
                    wordBreakUtil(s.substring(word.length()), wordDict, res, sb);
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        System.out.println(wordBreak(s, wordDict));
    }

}

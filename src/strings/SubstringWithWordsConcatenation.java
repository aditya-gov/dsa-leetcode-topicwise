package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithWordsConcatenation {

    public static List<Integer> findSubstring(String s, String[] words) {
        int sizeWord = words[0].length();
        int countWord = words.length;
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        int totalLen = sizeWord * countWord;
        if (totalLen > n) {
            return res;
        }
        Map<String, Integer> hm = new HashMap<>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i <= n - totalLen; i++) {
            HashMap<String, Integer> tempHm = new HashMap<>(hm);
            int j = i, count = countWord;
            while (j < i + totalLen) {
                String word = s.substring(j, j + sizeWord);
                if (!hm.containsKey(word) || tempHm.get(word) == 0) {
                    break;
                } else {
                    tempHm.put(word, tempHm.get(word) - 1);
                    count--;
                }
                j += sizeWord;
            }
            if (count == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(findSubstring(s, words));
    }
}

package strings;

import java.util.Arrays;

public class LongestKDistinctCharSubstring {

    public static int longestKSubstring(String s, int k) {
        int n = s.length();
        int l = 0;
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (int i=0; i<n; i++) {
            if (freq[s.charAt(i) - 'a'] == 0) {
                l++;
            }
            freq[s.charAt(i) - 'a']++;
        }
        if (l < k) {
            return -1;
        }
        int start = 0, end = 0;
        int max_win = 1;
        Arrays.fill(freq, 0);
        freq[s.charAt(0) - 'a']++;
        for (int i=1; i<n; i++) {
            freq[s.charAt(i) - 'a']++;
            end++;
            while(!isValid(freq, k)) {
                freq[s.charAt(start) - 'a']--;
                start++;
            }
            if (end - start + 1 > max_win) {
                max_win = end - start + 1;
            }
        }
        return max_win;
    }

    private static boolean isValid(int[] freq, int k) {
        int v = 0;
        for (int i=0; i<26; i++) {
            if (freq[i] > 0) {
                v++;
            }
        }
        return k >= v;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        System.out.println(longestKSubstring(s, k));
    }

}

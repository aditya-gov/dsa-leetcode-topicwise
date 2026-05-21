package SlidingWindow;

public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        String res = "";
        int[] letterCount = new int[128];
        for (char c : t.toCharArray()) {
            letterCount[c]++;
        }
        int left = 0, count = 0;
        int minLen = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            if (--letterCount[s.charAt(right)] >= 0) {
                count++;
            }
            while (count == t.length()) {
                if (minLen > right - left) {
                    minLen = right - left;
                    res = s.substring(left, right + 1);
                }
                if (++letterCount[s.charAt(left)] > 0) {
                    count--;
                }
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

}

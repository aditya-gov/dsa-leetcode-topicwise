package strings;

public class LongestSubstringWithoutRepeatingChars {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int res = 0;
        boolean[] visited = new boolean[256];
        int left = 0, right = 0;
        while (right < n) {
            if (visited[s.charAt(right)]) {
                while (visited[s.charAt(right)]) {
                    visited[s.charAt(left)] = false;
                    left++;
                }
            }
            visited[s.charAt(right)] = true;
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

}

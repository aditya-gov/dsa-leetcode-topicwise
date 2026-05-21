package strings;

import java.util.HashSet;
import java.util.Set;

public class PrintLongestSubstringWithoutRepeatingChars {

    public static String printLongestSubstring(String s) {
        int n = s.length();
        Set<Character> visited = new HashSet<>();
        int l = 0, r = 0;
        int maxStr = 0;
        int maxL = 0, maxR = 0;
        while (r < n) {
            if (!visited.contains(s.charAt(r))) {
                visited.add(s.charAt(r));
                if (r - l + 1 > maxStr) {
                    maxStr = r - l + 1;
                    maxL = l;
                    maxR = r;
                }
                r++;
            } else {
                visited.remove(s.charAt(l));
                l++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxL; i <= maxR; i++) {
            sb.append(s.charAt(i));
        }
        System.out.println("Length of longest Substring: " + (maxR - maxL + 1));
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test Case 1
        String str1 = "GEEKSFORGEEKS";
        System.out.println(PrintLongestSubstringWithoutRepeatingChars.printLongestSubstring(str1));
        // Test Case 2
        String str2 = "abcabcbb";
        System.out.println(PrintLongestSubstringWithoutRepeatingChars.printLongestSubstring(str2));
    }

}

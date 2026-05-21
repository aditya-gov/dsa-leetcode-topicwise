package strings;

public class LongestPalindromicSubstring5 {

    public static String longestPalindrome(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        int start = 0, end = 1;
        int low, high;
        for (int i = 1; i < n; i++) {
            // Find the longest palindromic substring of EVEN size
            low = i - 1;
            high = i;
            // Expand substring while it is palindrome and in bounds
            while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > end) { // If current palindrome is longer than our best
                    start = low; // Update start index
                    end = high - low + 1; // Update longest length
                }
                low--;
                high++;
            }
            // Find the longest palindromic substring of ODD size
            low = i - 1;
            high = i + 1;
            // Expand substring while it is palindrome and in bounds
            while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > end) { // If current palindrome is longer than our best
                    start = low; // Update start index
                    end = high - low + 1; // Update longest length
                }
                low--;
                high++;
            }
        }
        low = start;
        high = start + end;
        String str = s.substring(low, high);
        res.append(str);
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

}

package strings;

/**
 * Given two strings, s and t , both consisting of lowercase English letters,
 * return the number of substrings in s that are anagrams of t.
 *
 * An anagram is a word or phrase formed by rearranging the letters of
 * another word or phrase, using all the original letters exactly once.
 */
public class CountSubstringAnagrams {

    public static int countSubstringAnagrams(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenT > lenS) {
            return 0;
        }
        int count = 0;
        int[] expectedFreq = new int[26];
        int[] windowFreq = new int[26];
        // Populate 'expectedFreqs' with the characters in string 't'.
        for (char c : t.toCharArray()) {
            expectedFreq[c - 'a']++;
        }
        int left = 0, right = 0;
        while (right < lenS) {
            // Add the character at the right pointer to 'windowFreqs' before sliding the window.
            windowFreq[s.charAt(right) - 'a']++;
            // If the window has reached the expected fixed length, we advance the left
            // pointer as well as the right pointer to slide the window.
            if (right - left + 1 == lenT) {
                boolean isMatch = true;
                for (int i = 0; i < 26; i++) {
                    if (windowFreq[i] != expectedFreq[i]) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    count++;
                }
                // Remove the character at the left pointer from 'windowFreqs' before
                // advancing the left pointer.
                windowFreq[s.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "forxxorfxdofr";
        String t = "for";
        System.out.println(countSubstringAnagrams(s, t));
    }
}

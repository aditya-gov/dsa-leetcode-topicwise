package strings;

public class StringDifferByOneCharacter {

    public static String findString(String[] s) {
        if (s.length == 0) {
            return "-1";
        }
        String base = s[0];
        char[] baseCharArray = base.toCharArray();
        for (int i = 0; i < baseCharArray.length; i++) {
            char originalChar = baseCharArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) {
                    continue;
                }

                baseCharArray[i] = c;
                // Validate the candidate against all strings in the repository
                if (isValidCandidate(baseCharArray, s)) {
                    return String.valueOf(baseCharArray);
                }
                // Revert back to the original character before moving to the next position
                baseCharArray[i] = originalChar;
            }
        }
        return "-1";
    }

    private static boolean isValidCandidate(char[] baseCharArray, String[] s) {
        for (String word : s) {
            int diffCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (baseCharArray[i] != word.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) {
                        break;
                    }
                }
            }
            if (diffCount != 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str = { "abac", "zdac", "bdac"};
        System.out.println(findString(str));
    }

}

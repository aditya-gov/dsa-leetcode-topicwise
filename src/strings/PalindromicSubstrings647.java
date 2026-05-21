package strings;

public class PalindromicSubstrings647 {
    public static int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String temp = s.substring(i, j);
                if (!temp.isEmpty()) {
                    StringBuilder sb = new StringBuilder(temp);
                    sb.reverse();
                    if (sb.toString().compareTo(temp) == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(countSubstrings(s));
    }

}

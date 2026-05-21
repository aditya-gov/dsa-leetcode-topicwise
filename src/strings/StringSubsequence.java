package strings;

public class StringSubsequence {

    public static boolean isSubsequence(String s1, String s2, int n, int m) {
        if (m == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return isSubsequence(s1, s2, n - 1, m - 1);
        } else {
            return isSubsequence(s1, s2, n - 1, m);
        }
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "ac";
        System.out.println(isSubsequence(s1, s2, 3, 2));
    }

}

package strings;

public class ShortestPalindrome {

    public static String shortestPalindrome(String s) {
        int n = s.length();
        int j = 0;
        for (int i = n-1; i >= 0; i--) {
            if (s.charAt(j) == s.charAt(i)) {
                j++;
            } else {
                if (i != j) {
                    i = i + j;
                    j = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i>= j; i--) {
            sb.append(s.charAt(i));
        }
        return sb.append(s).toString();
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(shortestPalindrome(s));
    }

}

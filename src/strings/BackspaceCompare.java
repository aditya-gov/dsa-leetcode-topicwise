package strings;

public class BackspaceCompare {

    public static boolean backspaceCompare(String s, String t) {
        return getString(s).equals(getString(t));
    }

    private static String getString(String str) {
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == '#') {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "ab#c", t = "ad#c";
        System.out.println(backspaceCompare(s, t));
    }

}

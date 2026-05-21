package strings;

public class DecodeString {
    int i = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                String sub = decodeString(s);
                for (int j = 0; j < num; j++) {
                    sb.append(sub);
                }
                num = 0;
            } else if (ch == ']') {
                break;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString(s));
    }
}

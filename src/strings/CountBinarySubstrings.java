package strings;

public class CountBinarySubstrings {

    public static int countBinarySubstrings(String s) {
        int prevLen = 0, currLen = 1;
        int res = 0;
        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i) == s.charAt(i-1)) {
                currLen++;
            } else {
                prevLen = currLen;
                currLen = 1;
            }
            if(prevLen >= currLen) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "00110011";
        System.out.println(countBinarySubstrings(str));
    }

}

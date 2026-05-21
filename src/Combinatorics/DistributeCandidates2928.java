package Combinatorics;

public class DistributeCandidates2928 {

    public static int distributeCandies(int n, int limit) {
        int res = 0;
        for (int i = 0; i <= limit; i++) {
            int min = Math.max(0, n - i - limit);
            int max = Math.min(limit, n - i);
            if (max >= min) {
                res += (max - min + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        int limit = 2;
        System.out.println(distributeCandies(n, limit));
    }

}

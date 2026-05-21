package dp.lis;

import java.util.Arrays;

public class MaxLengthChainPairs {

    public static int maxLengthChain(int[][] pairs) {
        int n = pairs.length;
        int max = 0;
        int[] mlc = new int[n];
        Arrays.fill(mlc, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1] && mlc[i] < mlc[j] + 1) {
                    mlc[i] = mlc[j] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (max < mlc[i]) {
                max = mlc[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] pairs = {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90}};
        System.out.println(maxLengthChain(pairs));
    }
}

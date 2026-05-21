package dp.lis;

import java.util.Arrays;

public class MaxLengthBitonicSubsequence {

    public static int maxLengthBitonicSubsequence(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        int[] lds = new int[n];
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        // longest increasing subsequence
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        // longest decreasing subsequence
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }

        int max = lis[0] + lds[0] - 1;
        for (int i = 1; i < n; i++) {
            int temp = lis[i] + lds[i] - 1;
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println(maxLengthBitonicSubsequence(arr));
    }

}

package dp.lis;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static int longestIncreasingSubsequence(int[] arr) {
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (max < lis[i]) {
                max = lis[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(longestIncreasingSubsequence(arr));
    }

}

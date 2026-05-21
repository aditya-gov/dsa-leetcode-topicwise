package dp.lis;

public class MaximumSumIncreasingSubsequence {

    public static int maximumSumIncreasingSubsequence(int[] arr) {
        int max = 0;
        int n = arr.length;
        int[] msis = new int[n];

        for (int i = 0; i < n; i++) {
            msis[i] = arr[i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && msis[i] < msis[j] + arr[i]) {
                    msis[i] = msis[j] + arr[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(msis[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 101, 2, 3, 100, 4, 5};
        System.out.println(maximumSumIncreasingSubsequence(arr));
    }

}

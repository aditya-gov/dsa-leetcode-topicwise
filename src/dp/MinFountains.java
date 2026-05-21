package dp;

public class MinFountains {

    public int minCountFountains(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int idxLeft, idxRight;

        for (int i = 0; i < n; i++) {
            idxLeft = Math.max(i - arr[i], 0);
            idxRight = Math.min(i + arr[i], n);
            dp[idxLeft] = Math.max(dp[idxLeft], idxRight);
        }
        int minCount = 1;
        int idxNext = 0;
        int rightIdx = dp[0];
        for (int i = 0; i < n; i++) {
            if (i > rightIdx) {
                if (idxNext <= rightIdx) {
                    return -1;
                }
                minCount++;
                rightIdx = idxNext;
            }
            idxNext = Math.max(idxNext, dp[i]);
        }
        return minCount;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 1, 2, 1 };
        MinFountains solver = new MinFountains();
        System.out.printf("Minimum no of fountains: " + solver.minCountFountains(arr));
    }

}

package dp.knapsack;

public class UnboundedFractionalKnapsackRepetitionAllowed {

    public int knapSack(int capacity, int[] val, int[] wt) {
        int n = val.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c <= capacity; c++) {
                if (wt[i] <= c) {
                    // take = val[i] + dp[i][c - wt[i]]
                    // noTake = dp[i + 1][c]
                    dp[i][c] = Math.max(val[i] + dp[i][c - wt[i]], dp[i + 1][c]);
                }
            }
        }
        return dp[0][capacity];
    }

    public static void main(String[] args) {
        int[] val = { 1, 1 };
        int[] wt = { 2, 1 };
        int capacity = 3;
        UnboundedFractionalKnapsackRepetitionAllowed solver = new UnboundedFractionalKnapsackRepetitionAllowed();
        System.out.printf("Fractional Knapsack: " + solver.knapSack(capacity, val, wt));
    }

}

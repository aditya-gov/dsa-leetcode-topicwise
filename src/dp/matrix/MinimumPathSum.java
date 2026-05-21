package dp.matrix;

public class MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        /*dp[0][0] = grid[0][0];

        // first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + grid[i][j];
            }
        }*/

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    dp[0][j] = dp[0][j - 1] + grid[0][j]; // first row
                } else if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + grid[i][0]; // first column
                } else {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    private static int min(int x, int y, int z) {
        if (x < y && x < z) {
            return x;
        } else if (y < x && y < z) {
            return y;
        }
        return z;
    }

    public static void main(String[] args) {
        int[][] grid = {{ 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 }};
        System.out.println(minPathSum(grid));
    }

}

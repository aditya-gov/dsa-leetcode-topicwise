package backtracking;

/**
 * Given a matrix with positive weights and -1 (block, cannot move).
 * Find maximum weight path such that no path exists after the last reachable block.
 * Movement is possible in all 8 possible directions.
 */
public class MaxWeightPath {

    // 8 Directions: N, S, E, W, NE, NW, SE, SW
    private static final int[] dRow = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] dCol = {0, 0, 1, -1, 1, -1, 1, -1};

    private static int globalMaxWeight = 0;

    public static int solve(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        globalMaxWeight = 0;

        // Assuming path can start at any non-blocked cell.
        // If it must start at (0,0), just call dfs(matrix, visited, 0, 0, 0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != -1) {
                    boolean[][] visited = new boolean[rows][cols];
                    dfs(matrix, visited, i, j, 0);
                }
            }
        }
        return globalMaxWeight;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, int r, int c, int currentSum) {
        visited[r][c] = true;
        int cellWeight = matrix[r][c];
        currentSum += cellWeight;

        boolean canMoveFurther = false;

        for (int i = 0; i < 8; i++) {
            int nextR = r + dRow[i];
            int nextC = c + dCol[i];
            if (isValid(matrix, visited, nextR, nextC)) {
                canMoveFurther = true;
                dfs(matrix, visited, nextR, nextC, currentSum);
            }
        }

        // If no path exists after this cell (Dead End)
        if (!canMoveFurther) {
            globalMaxWeight = Math.max(globalMaxWeight, currentSum);
        }

        // Backtrack: Unmark visited to allow other path branches to use this cell
        visited[r][c] = false;
    }

    private static boolean isValid(int[][] matrix, boolean[][] visited, int r, int c) {
        return (r >= 0 && r < matrix.length &&
                c >= 0 && c < matrix[0].length &&
                matrix[r][c] != -1 && !visited[r][c]);
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 1,  2, -1},
                { 4,  5,  6},
                {-1,  8,  9}
        };
        System.out.println("Maximum Weight Path: " + solve(grid));
    }

}

package graph;

public class MaxFishInGrid {

    public int findMaxFish(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int maxFish = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != 0) {
                    maxFish = Math.max(maxFish, dfs(grid, i, j));
                }
            }
        }
        return maxFish;
    }

    private int dfs(int [][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int currFish = grid[i][j];
        grid[i][j] = 0; // to ensure it never revisit
        currFish += dfs(grid, i + 1, j);
        currFish += dfs(grid, i - 1, j);
        currFish += dfs(grid, i, j + 1);
        currFish += dfs(grid, i, j - 1);
        return currFish;
    }

    public static void main(String[] args) {
        MaxFishInGrid maxFishInGrid = new MaxFishInGrid();
        int[][] grid = {{0,2,1,0}, {4,0,0,3}, {1,0,0,4}, {0,3,2,0}};
        System.out.println("maximum number of fish: " + maxFishInGrid.findMaxFish(grid));
    }

}

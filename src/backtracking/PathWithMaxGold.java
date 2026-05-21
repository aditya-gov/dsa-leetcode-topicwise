package graph;

public class PathWithMaxGold {
    static int max = 0;
    static int r = 0, c = 0;

    public static int getMaximumGold(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return max;
    }

    private static void dfs(int [][] grid, int i, int j, int curr) {
        if (i < 0 || i >= r || j < 0 || j >= c || grid[i][j] == 0) {
            max = Math.max(curr, max);
            return;
        }
        int val = grid[i][j];
        grid[i][j] = 0;
        dfs(grid, i + 1, j, curr + val);
        dfs(grid, i - 1, j, curr + val);
        dfs(grid, i, j + 1, curr + val);
        dfs(grid, i, j - 1, curr + val);
        grid[i][j] = val;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,6,0}, {5,8,7}, {0,9,0} };
        System.out.println(getMaximumGold(grid));
    }

}

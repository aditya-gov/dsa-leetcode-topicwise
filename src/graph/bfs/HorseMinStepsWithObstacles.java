package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class HorseMinStepsWithObstacles {

    public int minStepsWithObstacles(int[][] grid, int[] source, int[] target) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Edge Case: If the source or target itself is an obstacle, it's impossible
        if (grid[source[0]][source[1]] == 1 || grid[target[0]][target[1]] == 1) {
            return -1;
        }

        // Edge Case: Already at the target
        if (source[0] == target[0] && source[1] == target[1]) {
            return 0;
        }

        // The 8 possible translations for a Knight's move
        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        q.offer(new int[]{source[0], source[1], 0});
        visited[source[0]][source[1]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];
            int steps = curr[2];

            for (int i = 0; i < 8; i++) {
                int nextX = currX + xMove[i];
                int nextY = currY + yMove[i];

                if (nextX == target[0] && nextY == target[1]) {
                    return steps + 1;
                }

                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols
                        && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, steps + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        
    }
}

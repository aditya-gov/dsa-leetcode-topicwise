package graph.dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Modified Dijkstra
 */
public class PathWithMinimumEffort {

    public static int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        // Min-heap: {effort, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {0, 0, 0});

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int effort = curr[0];
            int row = curr[1];
            int col = curr[2];

            if (row == rows - 1 && col == cols - 1) {
                return effort;
            }
            // Skip if already found better path
            if (effort > dist[row][col]) {
                continue;
            }
            // Explore neighbors
            for (int[] dir : dirs) {
                int newR = row + dir[0];
                int newC = col + dir[1];
                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
                    int diff = Math.abs(heights[newR][newC] - heights[row][col]);
                    int newEffort = Math.max(effort, diff);
                    if (newEffort < dist[newR][newC]) {
                        dist[newR][newC] = newEffort;
                        pq.offer(new int[] {newEffort, newR, newC});
                    }
                }
            }
        }
        return dist[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] heights = {{1,2,2}, {3,8,2}, {5,3,5}};
        System.out.println(minimumEffortPath(heights));
    }
}

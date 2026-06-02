package graph.floydWarshall;

import java.util.Arrays;

public class FindCityWithFewestReachable {

    public static int findTheCity(int n, int[][] edges, int threshold) {
        int INF = Integer.MAX_VALUE;
        // Step 1: Initialize the distance matrix
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // Distance to itself is 0
        }

        // Step 2: Populate the matrix with direct edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        // Step 3: Floyd-Warshall Algorithm to find all-pairs shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 4: Find the city with the fewest reachable neighbors
        int minReachableCount = n;
        int result = -1;
        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= threshold) {
                    reachable++;
                }
            }
            // If the reachable count is less than OR EQUAL TO the current minimum,
            // we update resultCity. The "equal to" handles the tie-breaker rule
            // because the loop iterates from 0 to n-1, naturally favoring larger indices.
            if (reachable <= minReachableCount) {
                minReachableCount = reachable;
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n1 = 4;
        int[][] edges1 = {
                {0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}
        };
        int threshold1 = 4;
        System.out.println(findTheCity(n1, edges1, threshold1));
    }
}

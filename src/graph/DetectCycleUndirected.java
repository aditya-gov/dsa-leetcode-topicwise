package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Detect cycle in an undirected graph
 */
public class DetectCycleUndirected {

    public static boolean detectCycle(int[][] edges, int V) {
        List<List<Integer>> adj = createAdjacencyList(V, edges);
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(adj, i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(List<List<Integer>> adj, int u, boolean[] visited, int parent) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (dfs(adj, v, visited, u)) {
                return true;
            } else if (v != parent) {
                return true;
            }
        }
        return false;
    }

    private static List<List<Integer>> createAdjacencyList(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }
        return adjList;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {0, 2},
                {0, 1},
                {3, 2}
        };
        int V = 4;
        System.out.println(detectCycle(edges, V));
    }

}

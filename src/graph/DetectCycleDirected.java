package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycleDirected {

    public static boolean detectCycle(int[][] edges, int V) {
        List<List<Integer>> adj = createAdjacencyList(edges, V);
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        boolean[] recStack = new boolean[V];
        Arrays.fill(recStack, false);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(adj, i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(List<List<Integer>> adj, int u, boolean[] visited, boolean[] recStack) {
        visited[u] = true;
        recStack[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[u] && dfs(adj, v, visited, recStack)) {
                return true;
            } else if (recStack[u]) {
                return true;
            }
        }
        recStack[u] = false;
        return false;
    }

    private static List<List<Integer>> createAdjacencyList(int[][] edges, int V) {
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
                {0, 1},
                {1, 2},
                {2, 0},
                {2, 3}
        };
        int V = 4;
        System.out.println(detectCycle(edges, V));
    }

}

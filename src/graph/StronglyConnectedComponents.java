package graph;

import java.util.*;


public class StronglyConnectedComponents {

    public List<List<Integer>> scc(int V, int[][] edges) {
        List<List<Integer>> adj = createAdjacencyList(V, edges);
        boolean[] visited = new boolean[V];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                List<Integer> sccComponent = new ArrayList<>();
                dfs(i, adj, visited, sccComponent);
                res.add(sccComponent);
            }
        }
        return res;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] visited, List<Integer> sccComponent) {
        visited[u] = true;
        sccComponent.add(u);
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, adj, visited, sccComponent);
            }
        }
    }

    private List<List<Integer>> createAdjacencyList(int V, int[][] edges) {
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
        int V = 5;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {3, 4}
        };
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        System.out.println(scc.scc(V, edges));
    }
}

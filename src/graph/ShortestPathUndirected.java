package graph;

import java.util.*;

public class ShortestPathUndirected {

    private static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private static void bfs(List<List<Integer>> adj, int V, int src, int[] dist) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    dist[v] = dist[u] + 1;
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public static void findShortestPath(int[][] edges, int V, int src) {
        List<List<Integer>> adj = createAdjacencyList(V, edges);
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        bfs(adj, V, src, dist);
        for(int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    private static List<List<Integer>> createAdjacencyList(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        /*for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }*/
        // alternative approach to add elements to the adjacency list
        for (int[] edge : edges) {
            addEdge(adjList, edge[0], edge[1]);
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
        findShortestPath(edges, V, 0);
    }

}

package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Shortest Path in a DAG
 */
public class ShortestPath {

    void shortestPath(int s, List<List<Integer>> adjList, int V) {
        Stack<Integer> stack = new Stack<>();
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortHelper(i, adjList, visited, stack);
            }
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (dist[u] != Integer.MAX_VALUE) {
                for (int v : adjList.get(u)) {
                    //if (dist[v] > dist[u] + weight)
                }
            }
        }
    }

    private void topologicalSortHelper(int u, List<List<Integer>> adjList, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                topologicalSortHelper(v, adjList, visited, stack);
            }
        }
        stack.push(u);
    }

    void createGraph(int V, List<List<Integer>> adjList) {
        List<List<Integer> > edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1, 2));
        edges.add(Arrays.asList(0, 4, 1));
        edges.add(Arrays.asList(1, 2, 3));
        edges.add(Arrays.asList(4, 2, 2));
        edges.add(Arrays.asList(4, 5, 4));
        edges.add(Arrays.asList(2, 3, 6));
        edges.add(Arrays.asList(5, 3, 1));

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            adjList.get(edge.get(0)).add(edge.get(1));
        }
    }
}



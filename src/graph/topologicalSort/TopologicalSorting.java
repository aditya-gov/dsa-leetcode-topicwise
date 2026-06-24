package graph.topologicalSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * DFS based topological sorting algorithm
 */
public class TopologicalSorting {

    public static void topologicalSort(List<List<Integer>> adj, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                topologicalSortUtil(u, adj, stack, visited);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void topologicalSortUtil(int u, List<List<Integer>> adj, Stack<Integer> stack, boolean[] visited) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                topologicalSortUtil(v, adj, stack, visited);
            }
        }
        stack.push(u);
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(0, 2));
        edges.add(Arrays.asList(2, 3));

        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> i : edges) {
            adj.get(i.get(0)).add(i.get(1));
        }
        topologicalSort(adj, V);
    }

}

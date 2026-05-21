package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length > n - 1) {
            return false;
        }
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        if (!dfs(0, -1, visited, adj)) {
            return false;
        }
        return visited.size() == n;
    }

    private boolean dfs(int u, int parent, Set<Integer> visited, List<List<Integer>> adj) {
        if (visited.contains(u)) {
            return false;
        }
        visited.add(u);
        for (int v : adj.get(u)) {
            if (v == parent) {
                continue;
            }
            if (!dfs(v, u, visited, adj)) {
                return false;
            }
        }
        return true;
    }
}


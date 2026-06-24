package graph.topologicalSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Shortest Path in a DAG
 */
public class ShortestPathDAG {

    static class Edge {
        int target;
        int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void shortestPath(int s, List<List<Edge>> adjList, int V) {
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
                for (Edge edge : adjList.get(u)) {
                    int  v = edge.target;
                    int weight = edge.weight;
                    if (dist[v] > dist[u] + weight) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }
        printShortestPaths(s, dist);
    }

    private static void topologicalSortHelper(int u, List<List<Edge>> adjList, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (Edge edge : adjList.get(u)) {
            if (!visited[edge.target]) {
                topologicalSortHelper(edge.target, adjList, visited, stack);
            }
        }
        stack.push(u);
    }

    private static void printShortestPaths(int s, int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(s + " -> " + i + " : INF");
            } else {
                System.out.println(s + " -> " + i + " : " + dist[i]);
            }
        }
    }

    private static void addEdge(List<List<Edge>> adjList, int u, int v, int weight) {
        adjList.get(u).add(new Edge(v, weight));
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        addEdge(adjList, 0, 1, 2);
        addEdge(adjList, 0, 4, 1);
        addEdge(adjList, 1, 2, 3);
        addEdge(adjList, 4, 2, 2);
        addEdge(adjList, 4, 5, 4);
        addEdge(adjList, 2, 3, 6);
        addEdge(adjList, 5, 3, 1);

        int source = 0;
        System.out.println("Shortest distances from source vertex " + source + ":");
        shortestPath(source, adjList, V);
    }
}



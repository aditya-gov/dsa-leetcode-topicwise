package graph.topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Kahn's algorithm to compute Topological Sorting using BFS approach
 */
public class TopologicalSortKahns {

    void topologicalSort(List<List<Integer>> adjList, int V) {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int u : adjList.get(i)) {
                inDegree[u]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adjList.get(u)) {
                if (--inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }
    }

    private List<List<Integer>> createAdjacencyList(int V) {
        List<List<Integer>> adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            addEdge(adjList, 0, 2);
            addEdge(adjList, 0, 3);
            addEdge(adjList, 1, 3);
            addEdge(adjList, 1, 4);
            addEdge(adjList, 2, 3);
        }
        return adjList;
    }

    private void addEdge(List<List<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
    }

    public static void main(String[] args) {
        int V = 5;
        TopologicalSortKahns topologicalSortKahns = new TopologicalSortKahns();
        List<List<Integer>> adjList = topologicalSortKahns.createAdjacencyList(V);
        System.out.println("Following is a Topological Sort of the given graph: ");
        topologicalSortKahns.topologicalSort(adjList, V);
    }

}

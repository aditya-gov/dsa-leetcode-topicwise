package graph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Kosaraju Algorithm
 */
public class SCCKosaraju {

    private final int V;
    private final LinkedList<Integer>[] adj;

    protected SCCKosaraju(int v) {
        this.V = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    private void addEdge(int v, int w) {
        adj[v].add(w);
    }

    private void DFSUtil(int v,boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    private SCCKosaraju getTranspose() {
        SCCKosaraju graph = new SCCKosaraju(V);
        for (int v = 0; v < V; v++) {
            for (Integer i : adj[v]) {
                graph.adj[i].add(v);
            }
        }
        return graph;
    }

    // topological sort
    private void fillOrder(int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                fillOrder(v, visited, stack);
            }
        }
        stack.push(u);
    }

    public void printSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }
        SCCKosaraju gr = getTranspose();

        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        while (!stack.empty()) {
            int v = stack.pop();
            if (!visited[v]) {
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        SCCKosaraju graph = new SCCKosaraju(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        System.out.println("Following are strongly connected components " +
                "in given graph ");
        graph.printSCCs();
    }

}

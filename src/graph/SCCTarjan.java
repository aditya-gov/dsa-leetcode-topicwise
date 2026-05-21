package graph;

import java.util.LinkedList;
import java.util.Stack;

public class SCCTarjan {

    private final int V;
    private final LinkedList<Integer>[] adj;
    private int time;

    public SCCTarjan(int v) {
        this.V = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
        time = 0;
    }

    private void addEdge(int v, int w) {
        adj[v].add(w);
    }

    private void printSCCs() {
        int[] low = new int[V];
        int[] disc = new int[V];
        for (int i = 0; i < V; i++) {
            disc[i] = -1;
            low[i] = -1;
        }
        boolean[] stackMember = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                SCCUtil(i, low, disc, stackMember, stack);
            }
        }
    }

    private void SCCUtil(int u, int[] low, int[] disc, boolean[] stackMember, Stack<Integer> stack) {
        disc[u] = time;
        low[u] = time;
        time += 1;
        stackMember[u] = true;
        stack.push(u);
        for (Integer v : adj[u]) {
            // If v is not visited yet, then recur for it
            // Case 1: Tree edge
            if (disc[v] == -1) {
                SCCUtil(v, low, disc, stackMember, stack);
                // Check if the subtree rooted with v has a connection to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);
            }
            // Update low value of u only if v is still in stack
            // Case 2: Back edge (not cross edge)
            else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        int w = -1;
        // If u is head node of SCC, pop the stack and store the SCC
        if (low[u] == disc[u]) {
            while (w != u) {
                w = stack.pop();
                System.out.print(w + " ");
                stackMember[w] = false;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SCCTarjan graph = new SCCTarjan(5);
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

package graph;

import java.util.LinkedList;

/**
 * Also known as cut vertices
 */
public class ArticulationPoint {


    private static final int NIL = -1;
    private final int V;
    private final LinkedList<Integer>[] adj;
    private int time = 0;

    ArticulationPoint(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }


    void articulationPoint() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        boolean[] ap = new boolean[V];

        for (int i = 0; i < V; i++) {
            parent[i] = NIL;
            visited[i] = false;
            ap[i] = false;
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                articulationPointUtil(i, visited, disc, low, parent, ap);
            }
        }
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private void articulationPointUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap) {
        int child = 0;
        visited[u] = true;
        disc[u] = low[u] = time++;
        for (int v : adj[u]) {
            if (!visited[v]) {
                child++;
                parent[v] = u;
                articulationPointUtil(v, visited, disc, low, parent, ap);
                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == NIL && child > 1) {
                    ap[u] = true;
                }
                if (parent[u] != NIL && low[v] >= disc[u]) {
                    ap[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Articulation points in first graph ");
        ArticulationPoint graph = new ArticulationPoint(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.articulationPoint();
        System.out.println();
    }

}

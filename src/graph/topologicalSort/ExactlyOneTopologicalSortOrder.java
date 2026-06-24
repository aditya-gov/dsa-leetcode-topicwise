package graph.topologicalSort;

import java.util.*;

/**
 * A topological ordering is unique iff there is exactly one node with indegree 0
 * at every step of Kahn's algorithm. This gives an O(V + E) solution.
 */
public class ExactlyOneTopologicalSortOrder {

    public List<Integer> exactlyOneSortOrder(int n, List<int[]> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            // More than one choice ==> Not unique
            if (queue.size() > 1) {
                return Collections.emptyList();
            }
            int current = queue.poll();
            order.add(current);
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // cycle exists
        if (order.size() != n) {
            return Collections.emptyList();
        }
        return order;
    }

    public static void main(String[] args) {
        List<int[]> edges = Arrays.asList(
                new int[] {0, 1},
                new int[] {1, 2},
                new int[] {2, 3}
        );
        ExactlyOneTopologicalSortOrder solution = new ExactlyOneTopologicalSortOrder();
        int n = 4;
        System.out.println(solution.exactlyOneSortOrder(n, edges));
    }
}

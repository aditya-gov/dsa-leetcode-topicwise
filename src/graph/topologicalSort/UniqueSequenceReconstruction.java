package graph.topologicalSort;

import java.util.*;

public class UniqueSequenceReconstruction {

    public List<Integer> reconstructUniqueSequence(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int[] pair : pairs) {
            int u = pair[0];
            int v = pair[1];
            if (!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            if (!graph.containsKey(v)) {
                graph.put(v, new ArrayList<>());
            }
            if (!inDegree.containsKey(u)) {
                inDegree.put(u, 0);
            }
            if (!inDegree.containsKey(v)) {
                inDegree.put(v, 0);
            }
            graph.get(u).add(v);
            inDegree.put(v, inDegree.get(v) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return Collections.emptyList();
            }
            int current = queue.poll();
            order.add(current);
            for (int neighbour : graph.get(current)) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        if (order.size() == inDegree.size()) {
            return order;
        } else {
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        UniqueSequenceReconstruction solution = new UniqueSequenceReconstruction();
        // exactly one valid ordering
        int[][] pairs1 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(solution.reconstructUniqueSequence(pairs1));
        // more than one valid ordering
        int[][] pairs2 = {{1, 2}, {1, 3}, {2, 4}, {3, 4}};
        System.out.println(solution.reconstructUniqueSequence(pairs2));
        // no valid ordering (CYCLE)
        int[][] pairs3 = {{1, 2}, {2, 3}, {3, 1}};
        System.out.println(solution.reconstructUniqueSequence(pairs3));
    }

}

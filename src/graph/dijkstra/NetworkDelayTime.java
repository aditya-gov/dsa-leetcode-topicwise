package graph.dijkstra;

import java.util.*;

public class NetworkDelayTime {

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], val -> new ArrayList<>()).add(new int[] {time[1], time[2]});
        }

        Map<Integer, Integer> distances = new HashMap<>();
        distances.put(k, 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {0, k});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dist = curr[0];
            int node = curr[1];
            if (dist > distances.getOrDefault(node, Integer.MAX_VALUE)) {
                continue;
            }
            for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
                int neighbour = edge[0];
                int weight = edge[1];
                int newDist = dist + weight;
                if (newDist < distances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    distances.put(neighbour, newDist);
                    pq.offer(new int[] {newDist, neighbour});
                }
            }
        }
        if (distances.size() != n) {
            return -1;
        }
        return Collections.max(distances.values());
    }

    public static void main(String[] args) {
        int[][] times = {{1,2,2}, {3,2,1}};
        int n = 3;
        int k = 1;
        System.out.println(networkDelayTime(times, n, k));
    }

}

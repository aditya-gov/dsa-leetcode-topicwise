package graph.dijkstra;

import java.util.*;

/**
 * Hybrid Dijkstra by extending its state with additional constraints
 */
public class CheapestPathWithinKStops {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build Adjacency List Map<City, List<[NextCity, Price]>>
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], val -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }
        // {cost, city, stopsUsed}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src, 0});

        Map<String, Integer> best = new HashMap<>();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int city = curr[1];
            int stops = curr[2];
            if (city == dst) {
                return cost;
            }
            if (stops > k) {
                continue;
            }
            String key = city + "," + stops;
            if (best.containsKey(key) && best.get(key) <= cost) {
                continue;
            }
            best.put(key, cost);
            for (int[] next : graph.getOrDefault(city, new ArrayList<>())) {
                int newCost = cost + next[1];
                pq.offer(new int[] {newCost, next[0], stops + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // [[0, 1, 100], [1, 2, 200], [0, 2, 500], src = 0, dst = 2, k = 1
        int[][] flights = {{0, 1, 100}, {1, 2, 200}, {0, 2, 500}};
        int src = 0, dst = 2;
        int k = 1;
        int n = 3;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }

}

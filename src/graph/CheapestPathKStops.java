package graph;

import java.util.*;

/**
 * Time Complexity: O(KE), Space Complexity: O(V + E)
 */

public class CheapestPathKStops {

    public int findCheapestWithKStops(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build Adjacency List Map<City, List<[NextCity, Price]>>
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], val -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        // Step 2: Track min fares to prune expensive redundant paths
        int[] minFares = new int[n];
        Arrays.fill(minFares, Integer.MAX_VALUE);
        minFares[src] = 0;

        // Step 3: Standard Queue for BFS. Array holds: [current_city, cost_so_far]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        int stops = 0;

        // Step 4: Level-by-level BFS
        // k stops means k + 1 edges/flights allowed
        while (!queue.isEmpty() && stops <= k) {
            int levelSize = queue.size();

            // Process everything at the current "stop" level before moving to the next
            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int city = current[0];
                int currentCost = current[1];

                if (!graph.containsKey(city)) continue;

                for (int[] neighbor : graph.get(city)) {
                    int nextCity = neighbor[0];
                    int flightPrice = neighbor[1];
                    int totalCost = currentCost + flightPrice;

                    // Only add to queue if this path to nextCity is the cheapest we've found SO FAR
                    if (totalCost < minFares[nextCity]) {
                        minFares[nextCity] = totalCost;
                        queue.offer(new int[]{nextCity, totalCost});
                    }
                }
            }
            stops++;
        }
        return minFares[dst] == Integer.MAX_VALUE ? -1 : minFares[dst];
    }

    public static void main(String[] args) {
        // [[0, 1, 100], [1, 2, 200], [0, 2, 500], src = 0, dst = 2, k = 1
    }
}

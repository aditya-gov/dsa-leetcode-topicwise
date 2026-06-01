package graph.bellmanford;

import java.util.Arrays;

public class CheapestPathWithinKStops {

    public static int findCheapestWithKStops(int n, int[][] flights, int src, int dst, int k) {
        final int INF = Integer.MAX_VALUE;
        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        cost[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(cost, n);
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if (cost[from] == INF) {
                    continue;
                }
                next[to] = Math.min(next[to], cost[from] + price);
            }
            cost = next;
        }
        return cost[dst] == INF ? -1 : cost[dst];
    }

    public static void main(String[] args) {
        // [[0, 1, 100], [1, 2, 200], [0, 2, 500], src = 0, dst = 2, k = 1
        int[][] flights = {{0, 1, 100}, {1, 2, 200}, {0, 2, 500}};
        int src = 0, dst = 2;
        int k = 1;
        int n = 3;
        System.out.println(findCheapestWithKStops(n, flights, src, dst, k));
    }
}

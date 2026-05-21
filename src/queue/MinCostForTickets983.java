package queue;

import java.util.LinkedList;
import java.util.Queue;

public class MinCostForTickets983 {

    public static int minCostTickets(int[] days, int[] costs) {
        Queue<Pair<Integer, Integer>> qMonth = new LinkedList<>();
        Queue<Pair<Integer, Integer>> qWeek = new LinkedList<>();

        int res = 0;
        for (int day : days) {
            while (!qMonth.isEmpty() && qMonth.peek().getKey() + 30 <= day) {
                qMonth.poll();
            }
            while (!qWeek.isEmpty() && qWeek.peek().getKey() + 7 <= day) {
                qWeek.poll();
            }
            qMonth.add(new Pair<>(day, res + costs[2]));
            qWeek.add(new Pair<>(day, res + costs[1]));

            res = Math.min(res + costs[0], Math.min(qMonth.peek().getValue(), qWeek.peek().getValue()));
        }
        return res;
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        int[] days = new int[] {1,4,6,7,8,20};
        int[] costs = new int[] {2,7,15};
        System.out.println(minCostTickets(days, costs));
    }

}

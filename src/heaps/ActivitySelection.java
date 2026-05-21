package heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ActivitySelection {

    public static int activitySelection(int[] start, int[] finish) {
        int n = start.length;
        int res = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // sort by finish time
        for (int i = 0; i < n; i++) {
            pq.add(new int[] {start[i], finish[i]});
        }

        int finishTime = -1;
        while (!pq.isEmpty()) {
            int[] activity = pq.poll();
            if (activity[0] > finishTime) {
                finishTime = activity[1];
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] start = { 1, 3, 0, 5, 8, 5 };
        int[] finish = { 2, 4, 6, 7, 9, 9 };
        System.out.println(ActivitySelection.activitySelection(start, finish));
    }
}

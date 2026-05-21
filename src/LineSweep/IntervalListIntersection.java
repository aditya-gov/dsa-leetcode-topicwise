package LineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntervalListIntersection {

    static class Interval implements Comparable<Interval> {
        int time;
        int type; // 1 for START, -1 for END

        public Interval(int time, int type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Interval o) {
            if (this.time != o.time) {
                return this.time - o.time;
            }
            // CRITICAL: At the same coordinate, process START (+1)
            // before END (-1) to catch point intersections like [5, 5]
            return o.type - this.type;
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[0][0];
        }

        // 1. Transform intervals into events
        List<Interval> intervals = new ArrayList<>();
        for (int[] a : A) {
            intervals.add(new Interval(a[0], 1));
            intervals.add(new Interval(a[1], -1));
        }
        for (int[] b : B) {
            intervals.add(new Interval(b[0], 1));
            intervals.add(new Interval(b[1], -1));
        }

        // 2. Sort all events
        Collections.sort(intervals);

        // 3. Sweep
        List<int[]> result = new ArrayList<>();
        int activeCount = 0;
        int intersectionStart = 0;

        for (Interval interval : intervals) {
            if (interval.type == 1) { // START event
                activeCount++;
                // When count becomes 2, an intersection begins
                if (activeCount == 2) {
                    intersectionStart += interval.time;
                }
            } else { // END event
                if (activeCount == 2 && intersectionStart != 0) {
                    result.add(new int[]{intersectionStart, interval.time});
                    intersectionStart = 0;
                }
                activeCount--;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        IntervalListIntersection solver = new IntervalListIntersection();
        int[][] interval1 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] interval2 = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println("Interval List Intersection 1: " + Arrays.deepToString(solver.intervalIntersection(interval1, interval2)));
    }

}

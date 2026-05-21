package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a 2D array intervals[][], where intervals[i] = [starti, endi].
 * Find the minimum number of intervals need to be removed to make the rest of the intervals non-overlapping.
 * Two intervals are considered non-overlapping if the end time of one interval is less than
 * or equal to the start time of the next interval.
 */
public class NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int count = 1;
        int finish = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (finish <= intervals[i][0]) { // overlap
                count++;
                finish = intervals[i][1];
            }
        }
        return n - count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,100},{11,22},{1,11},{2,12}};
        //int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        //int[][] intervals = {{1,2},{2,3}};
        //int[][] intervals = {{1,2},{1,2},{1,2}};
        //int[][] intervals = {{1, 2}, {2, 3}, {3, 5}, {1, 4}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

}

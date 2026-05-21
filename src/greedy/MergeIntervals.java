package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/***
 * Given an array of time intervals where arr[i] = [starti, endi],
 * our task is to merge all the overlapping intervals into one and
 * output the result which should have only mutually exclusive intervals.
 */
public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> temp = new ArrayList<>();
        int start = intervals[0][0];
        int finish = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (finish >= intervals[i][0]) {
                if (finish < intervals[i][1]) {
                    finish = intervals[i][1];
                }
                continue;
            }
            temp.add(new int[] {start, finish});
            start = intervals[i][0];
            finish = intervals[i][1];
        }
        temp.add(new int[] {start, finish});
        int[][] res = new int[temp.size()][2];
        int j = 0;
        for (int[] r : temp) {
            res[j][0] = r[0];
            res[j][1] = r[1];
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println((Arrays.deepToString(merge(intervals1))));
        int[][] intervals2 = {{7, 8}, {1, 5}, {2, 4}, {4, 6}};
        System.out.println(Arrays.deepToString(merge(intervals2)));

    }

}

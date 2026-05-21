package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervalsMethod2 {

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        // sort intervals based on the start values
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> temp = new ArrayList<>();
        int start = intervals[0][0];
        int finish = intervals[0][1];
        temp.add(new int[]{start, finish});

        for (int i = 1; i < n; i++) {
            int[] last = temp.get(temp.size() - 1);
            int[] curr = intervals[i];
            // If current interval overlaps with the last merged interval, merge them
            if (curr[0] <= last[1]) {
                last[1] = Math.max(last[1], curr[1]);
            } else {
                temp.add(new int[]{curr[0], curr[1]});
            }
        }
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

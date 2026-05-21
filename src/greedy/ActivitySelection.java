package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {

    public static int activitySelection(int[] start, int[] finish) {
        int n = start.length;
        int[][] pairs = new int[n][2];
        int res = 1;
        for (int i = 0; i < n; i++) {
            pairs[i][0] = start[i];
            pairs[i][1] = finish[i];
        }
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1])); // sort by finish time
        int finishTime = pairs[0][1];

        for (int i = 1; i < n; i++) {
            if (pairs[i][0] >= finishTime) {
                res++;
                finishTime = pairs[i][1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] start1 = { 1, 3, 0, 5, 8, 5 };
        int[] finish1 = { 2, 4, 6, 7, 9, 9 };
        System.out.println(activitySelection(start1, finish1));
        int[] start2 = { 12, 10, 20 };
        int[] finish2 = { 25, 20, 30 };
        System.out.println(activitySelection(start2, finish2));
    }

}

package heaps;

import java.util.Arrays;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int m = tasks.length;
        if(m == 0) {
            return 0;
        }
        int[] count = new int[26];

        for(char c : tasks) {
            count[c - 'A']++;
        }
        Arrays.sort(count);
        int max = count[25] - 1;
        int spaces = max * n;

        for(int i = 24; i >= 0; i--) {
            spaces -= Math.min(max, count[i]);
        }
        spaces = Math.max(0, spaces);

        return m + spaces;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        TaskScheduler taskScheduler = new TaskScheduler();
        System.out.println("Minimum number of CPU intervals required to complete all tasks: "
                + taskScheduler.leastInterval(tasks, n));
    }
}

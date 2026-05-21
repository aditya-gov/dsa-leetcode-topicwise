package greedy;

/**
 * <a href="https://leetcode.com/problems/task-scheduler/">...</a>
 */
public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        // Step 1: Count frequencies of each task
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        // Step 2: Find the max frequency
        int maxFreq = 0;
        for (int freq : count) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // Step 3: Count how many tasks have that maximum frequency
        int maxFreqCount = 0;
        for (int freq : count) {
            if (freq == maxFreq) {
                maxFreqCount++;
            }
        }

        // Step 4: Calculate the minimum intervals required by the formula
        // Note: Each chunk must have a length of (n + 1) slots (1 slot for the task itself + n cooling slots).
        int minIntervals = (maxFreq - 1) * (n + 1) + maxFreqCount;

        // Step 5: Return the larger value between the formula and total task count
        return Math.max(minIntervals, tasks.length);
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }

    /**
     * Time Complexity: O(M), where M is the total number of tasks in the input array. We iterate through the tasks array exactly once to count frequencies.
     * The subsequent loops run a constant 26 times, making them O(1).
     * Space Complexity: O(1) constant space. The tracking array size is fixed at 26 elements because the tasks are strictly uppercase English letters.
     */
}

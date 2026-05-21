package greedy;

import java.util.*;

public class JobSequencing {

    public static List<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;
        List<Integer> res = new ArrayList<>(Arrays.asList(0, 0));
        List<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{deadline[i], profit[i]});
        }
        // sort jobs in ascending order of deadline
        jobs.sort(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < jobs.size(); i++) {
            int[] job = jobs.get(i);
            // if job can be scheduled within its deadline
            if (job[0] > pq.size()) {
                pq.add(job[1]);
            } else if (!pq.isEmpty() && pq.peek() < job[1]) { // replace the job with the lowest profit
                pq.poll();
                pq.add(job[1]);
            }
        }
        while (!pq.isEmpty()) {
            res.set(1, res.get(1) + pq.poll());
            res.set(0, res.get(0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] deadline = {2, 1, 2, 1, 1};
        int[] profit = {100, 19, 27, 25, 15};
        List<Integer> result = jobSequencing(deadline, profit);
        System.out.println(result.get(0) + " " + result.get(1));
    }
}

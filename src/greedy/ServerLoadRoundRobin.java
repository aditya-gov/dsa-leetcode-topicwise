package greedy;

import java.util.*;

public class ServerLoadRoundRobin {

    public static int[] serverLoads(int n, int m, int[] arrivalTime, int[] processTime) {
        int[] serverLoad = new int[m];

        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            availableServers.add(i);
        }
        for (int i = 0; i < n; i++) {
            int endTime = arrivalTime[i] + processTime[i];

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime[i]) {
                int[] releasedServer = busyServers.poll();
                availableServers.add(releasedServer[1]);
            }
            if (availableServers.isEmpty()) {
                continue;
            }
            int demandedServer = (i % m);
            Integer assignedServer = availableServers.ceiling(demandedServer);
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            serverLoad[assignedServer]++;
            availableServers.remove(assignedServer);
            busyServers.offer(new int[] {endTime, assignedServer});
        }
        return serverLoad;
    }

    public static void main(String[] args) {
        int n = 4, m = 2;
        int[] arrivalTime = { 1, 2, 4, 6 };
        int[] processTime = { 7, 1, 4, 4 };
        System.out.println(Arrays.toString(serverLoads(n, m, arrivalTime, processTime)));
    }

}

package heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode #2402
 */
public class MeetingRooms3 {

    public static int mostBooked(int n, int[][] meetings) {
        //Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int res = 0;

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // min heap

        for (int i = 0; i < n; i++) {
            q.add(new int[] {0, i}); // {time, meetingRoom}
        }
        int[] rooms = new int[n];
        for (int[] meeting : meetings) {
            int time = meeting[0];
            while (!q.isEmpty() && q.peek()[0] < time) {
                q.add(new int[] {time, q.poll()[1]});
            }
            int[] currMeet = q.poll();
            int currRoom = currMeet[1];
            int endTime = currMeet[0] + (meeting[1] - meeting[0]);
            rooms[currRoom]++;

            if (rooms[currRoom] > rooms[res]) {
                res = currRoom;
            } else if (rooms[currRoom] == rooms[res]) {
                res = Math.min(res, currRoom);
            }
            q.add(new int[] {endTime, currRoom});
        }
        return res;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[][] meetings1 = {{0,10},{1,5},{2,7},{3,4}};
        int n1 = 2;
        System.out.println(MeetingRooms3.mostBooked(n1, meetings1));
        // Test Case 2
        int[][] meetings2 = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        int n2 = 3;
        System.out.println(MeetingRooms3.mostBooked(n2, meetings2));
        // Test Case 3
        int n3 = 1;
        int[][] meetings3 = {{1,2},{3,4},{0,6},{5,7},{8,9},{5,9}};
        System.out.println(MeetingRooms3.mostBooked(n3, meetings3));
    }

}

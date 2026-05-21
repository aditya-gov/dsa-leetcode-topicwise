package LineSweep;

import java.util.*;

public class MeetingRooms3 {

    // Represents a meeting
    static class Meeting {
        int start;
        int end;
        int originalIndex; // To potentially track original order if needed

        public Meeting(int start, int end, int originalIndex) {
            this.start = start;
            this.end = end;
            this.originalIndex = originalIndex;
        }
    }

    public int roomWithMostMeetings(int n, int[][] meetings) {
        // Sort meetings by original start time
        List<Meeting> sortedMeetings = new ArrayList<>();
        for (int i = 0; i < meetings.length; i++) {
            sortedMeetings.add(new Meeting(meetings[i][0], meetings[i][1], i));
        }
        sortedMeetings.sort(Comparator.comparingInt(a -> a.start));

        // Priority queue for available rooms (sorted by room index)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        // Priority queue for ongoing meetings (sorted by finish time, then room index for ties)
        PriorityQueue<long[]> ongoingMeetings = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Integer.compare((int) a[1], (int) b[1]);
        }); // {finish_time, room_number}

        // Priority queue for delayed meetings (sorted by original start time)
        PriorityQueue<Meeting> delayedMeetings = new PriorityQueue<>((a, b) -> a.start - b.start);

        // Array to store meeting counts per room
        int[] meetingCounts = new int[n];

        long currentTime = 0; // The sweep line

        int meetingIndex = 0;
        while (meetingIndex < sortedMeetings.size() || !delayedMeetings.isEmpty() || !ongoingMeetings.isEmpty()) {

            // If there are ongoing meetings, the next event time is either the start of the next meeting
            // or the finish time of the earliest finishing ongoing meeting.
            if (meetingIndex < sortedMeetings.size() && (ongoingMeetings.isEmpty() || sortedMeetings.get(meetingIndex).start <= ongoingMeetings.peek()[0])) {
                currentTime = sortedMeetings.get(meetingIndex).start;
            } else if (!ongoingMeetings.isEmpty()) {
                currentTime = ongoingMeetings.peek()[0];
            } else if (!delayedMeetings.isEmpty()) {
                // If no ongoing meetings and no more original meetings, jump to the start of the earliest delayed meeting
                currentTime = delayedMeetings.peek().start; // Note: This might need adjustment based on when rooms become free
            } else {
                // Should not happen if loop condition is correct
                break;
            }


            // Free up rooms for meetings that have finished by or at the current time
            while (!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] <= currentTime) {
                long[] finishedMeeting = ongoingMeetings.poll();
                int roomIdx = (int) finishedMeeting[1];
                availableRooms.offer(roomIdx);

                // Try to assign delayed meetings to newly available rooms
                while (!availableRooms.isEmpty() && !delayedMeetings.isEmpty()) {
                    int nextRoom = availableRooms.poll();
                    Meeting nextDelayedMeeting = delayedMeetings.poll();

                    // Calculate the actual finish time for the delayed meeting
                    long actualFinishTime = currentTime + (nextDelayedMeeting.end - nextDelayedMeeting.start);

                    ongoingMeetings.offer(new long[]{actualFinishTime, nextRoom});
                    meetingCounts[nextRoom]++;
                }
            }

            // If there are still original meetings to process and the current meeting starts at currentTime
            if (meetingIndex < sortedMeetings.size() && sortedMeetings.get(meetingIndex).start == currentTime) {
                Meeting currentMeeting = sortedMeetings.get(meetingIndex);

                // Try to assign the current meeting
                if (!availableRooms.isEmpty()) {
                    int roomIdx = availableRooms.poll();
                    ongoingMeetings.offer(new long[]{currentMeeting.end, roomIdx});
                    meetingCounts[roomIdx]++;
                } else {
                    // No available rooms, delay the meeting
                    delayedMeetings.offer(currentMeeting);
                }
                meetingIndex++; // Move to the next original meeting
            }
            // If no original meeting starts exactly at currentTime, we just processed room freeing events
        }


        // Find the room with the maximum meeting count
        int maxMeetings = -1;
        int roomWithMaxMeetings = -1;
        for (int i = 0; i < n; i++) {
            if (meetingCounts[i] > maxMeetings) {
                maxMeetings = meetingCounts[i];
                roomWithMaxMeetings = i;
            } else if (meetingCounts[i] == maxMeetings) {
                // Tie-breaking: choose the room with the smallest index
                roomWithMaxMeetings = Math.min(roomWithMaxMeetings, i);
            }
        }

        return roomWithMaxMeetings;
    }

    public static void main(String[] args) {
        MeetingRooms3 solver = new MeetingRooms3();

        // Example 1 (similar to a common test case for this problem type)
        int n1 = 2;
        int[][] meetings1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
        System.out.println("Room with most meetings for Example 1: " + solver.roomWithMostMeetings(n1, meetings1)); // Expected: 0

        // Example 2
        int n2 = 3;
        int[][] meetings2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
        System.out.println("Room with most meetings for Example 2: " + solver.roomWithMostMeetings(n2, meetings2)); // Expected: 1
    }
}

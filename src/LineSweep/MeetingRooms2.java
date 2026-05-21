package LineSweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms2 {

    /**
     * Solves Meeting Rooms II problem: Find the minimum number of conference rooms required.
     * Finds the maximum number of concurrent meetings.
     *
     * @param intervals A 2D array where intervals[i] = [start_i, end_i].
     * @return The minimum number of conference rooms required.
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 1. Create events
        List<Event> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new Event(interval[0], 1)); // Start event
            events.add(new Event(interval[1], -1)); // End event
        }

        // 2. Sort all events
        Collections.sort(events);

        // 3. Sweep
        int currentMeetings = 0;
        int maxMeetings = 0;

        for (Event event : events) {
            currentMeetings += event.type;
            // Update the maximum number of concurrent meetings
            maxMeetings = Math.max(maxMeetings, currentMeetings);
        }

        return maxMeetings;
    }

    // Helper class to represent events (start or end of a meeting)
    static class Event implements Comparable<Event> {
        int time;
        int type; // +1 for start, -1 for end

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }

        // Sort(ascending) primarily by time, secondarily by type (-1 comes before +1 for ties)
        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return this.time - other.time; // sort(ascending) primarily by time
            }
            return this.type - other.type; // sort(ascending) secondarily by type
        }
    }

    public static void main(String[] args) {
        MeetingRooms2 solver = new MeetingRooms2();

        // Example for Meeting Rooms II
        int[][] intervals3 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Minimum meeting rooms for 3: " + solver.minMeetingRooms(intervals3)); // Expected: 2

        int[][] intervals4 = {{7, 10}, {2, 4}};
        System.out.println("Minimum meeting rooms for 4: " + solver.minMeetingRooms(intervals4)); // Expected: 1

        int[][] intervals5 = {{1, 18}, {18, 23}, {15, 29}, {4, 15}, {2, 11}, {5, 13}};
        System.out.println("Minimum meeting rooms for 5: " + solver.minMeetingRooms(intervals5)); // Expected: 4
    }
}

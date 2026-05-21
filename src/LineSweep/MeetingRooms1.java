package LineSweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms1 {

    /**
     * Solves Meeting Rooms I problem: Can a person attend all meetings?
     * Checks if there are any overlaps.
     *
     * @param intervals A 2D array where intervals[i] = [start_i, end_i].
     * @return true if a person can attend all meetings, false otherwise.
     */

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
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
        for (Event event : events) {
            currentMeetings += event.type;
            // If at any point, the number of active meetings exceeds 1, there's an overlap
            if (currentMeetings > 1) {
                return false;
            }
        }
        return true;
    }

    // Helper class to represent events (start or end of a meeting)
    static class Event implements Comparable<Event> {
        int time;
        int type; // 1 for START, -1 for END

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }

        // Sort (ascending) primarily by time, secondarily by type (-1 comes before +1 for ties)
        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return this.time - other.time; // sort(ascending) primarily by time
            }
            return this.type - other.type; // sort(ascending) secondarily by type (start / end) time
        }
    }

    public static void main(String[] args) {
        MeetingRooms1 solver = new MeetingRooms1();

        // Example for Meeting Rooms I
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Can attend all meetings 1? " + solver.canAttendMeetings(intervals1)); // Expected: false

        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println("Can attend all meetings 2? " + solver.canAttendMeetings(intervals2)); // Expected: true

    }

    /**
     * Explanation:

     * 1. Event Class: We define a simple class Event to store the time of an event and its type.
     *      type = 1 signifies the start of a meeting, and type = -1 signifies the end of a meeting.

     * 2. compareTo Method: The compareTo method in the Event class is crucial for sorting. It first sorts events by time in ascending order.
     *      If two events have the same time, it sorts them by type, ensuring that end events (-1) come before start events (+1).
     *      This is important because a meeting ending at time t frees up a room before a meeting starting at time t requires a room.

     * 3. canAttendMeetings Method (Meeting Rooms I):
     *      It creates a list of Event objects from the input intervals, adding both a start and an end event for each meeting.
     *      The events list is sorted using our custom compareTo method.
     *      It iterates through the sorted events, maintaining a currentMeetings counter.
     *      For a start event, the counter is incremented; for an end event, it's decremented.
     *      If currentMeetings ever goes above 1, it means there's an overlap, and the function returns false.
     *      If the loop completes without the counter exceeding 1, it means no overlaps occurred, and the function returns true.
     */

}

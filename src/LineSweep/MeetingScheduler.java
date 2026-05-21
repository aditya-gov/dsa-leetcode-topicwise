package LineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people
 * and a meeting duration, return the earliest time slot that works for
 * both of them and is of duration. If there is no common time slot
 * that satisfies the requirements, return an empty array.
 * GFG link: <a href="https://www.geeksforgeeks.org/dsa/meeting-scheduler-for-two-persons/">...</a>
 */
public class MeetingScheduler {

    static class Event implements Comparable<Event> {
        int time;
        int type; // 1 for START, -1 for END

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event o) {
            if (this.time != o.time) {
                return Integer.compare(this.time, o.time);
            }
            // Process START before END at the same timestamp
            return Integer.compare(o.type, this.type);
        }
    }

    public static int[] minAvailableDuration(int[][] slot1, int[][] slot2, int d) {
        List<Event> events = new ArrayList<>();

        // 1. Create events
        for (int[] slot : slot1) {
            events.add(new Event(slot[0], 1));
            events.add(new Event(slot[1], -1));
        }
        for (int[] slot : slot2) {
            events.add(new Event(slot[0], 1));
            events.add(new Event(slot[1], -1));
        }

        // 2. Sort all events
        Collections.sort(events);

        // 3. Sweep
        int activeCount = 0;
        Integer overlapStart = null;
        for (Event event : events) {
            if (event.type == 1) { // START event
                activeCount++;
                if (activeCount == 2) {
                    overlapStart = event.time;
                }
            } else { // END event
                if (activeCount == 2 && overlapStart != null) {
                    // Check if the window that just closed was long enough
                    if (event.time - overlapStart >= d) {
                        return new int[]{overlapStart, overlapStart + d};
                    }
                    overlapStart = null;
                }
                activeCount--;
            }
        }
        return new int[0]; // no suitable slot found
    }

    public static void main(String[] args) {
        int[][] slot1 = new int[][] {
                {10, 50},
                {60, 120},
                {140, 210}
        };
        int[][] slot2 = new int[][] {
                {0, 15},
                {60, 70}
        };
        int d = 8;
        System.out.println(Arrays.toString(minAvailableDuration(slot1, slot2, d)));
    }

}

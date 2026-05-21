package LineSweep;

import java.util.*;

public class MergeIntervals {

    static class Event implements Comparable<Event> {
        int time;
        int type; // 1 for START, -1 for END

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time != other.time) {
                return Integer.compare(this.time, other.time);
            }
            return Integer.compare(other.type, this.type);
        }
    }

    public static int[][] mergeIntervals(int[][] intervals) {
        List<Event> events = new ArrayList<>();

        // Step 1: Create events
        for (int[] interval : intervals) {
            events.add(new Event(interval[0], 1));
            events.add(new Event(interval[1], -1));
        }

        // Step 2: Sort all events
        Collections.sort(events);

        // Step 3: Sweep
        List<int[]> mergedIntervals = new ArrayList<>();
        int activeIntervals = 0;
        int currStart = 0;

        for (Event event : events) {
            if (activeIntervals == 0) {
                currStart = event.time;
            }
            activeIntervals += event.type;
            if (activeIntervals == 0) {
                mergedIntervals.add(new int[]{currStart, event.time});
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println((Arrays.deepToString(mergeIntervals(intervals1))));
        int[][] intervals2 = {{7, 8}, {1, 5}, {2, 4}, {4, 6}};
        System.out.println(Arrays.deepToString(mergeIntervals(intervals2)));
    }
}

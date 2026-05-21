package LineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeFreeTime {

    static class Event implements Comparable<Event> {
        int time;
        int type;

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }

        public int compareTo(Event o) {
            if (this.time != o.time) {
                return Integer.compare(this.time, o.time);
            }
            // Tie-break: Process START (1) before END (-1).
            // This ensures that if one person starts exactly when another ends,
            // the activeCount never hits zero, correctly showing no free time.
            return Integer.compare(o.type, this.type);
        }
    }

    public static int[][] employeeFreeTime(int[][][] schedule) {
        List<Event> events = new ArrayList<>();

        //1. Create events
        for (int[][] employee: schedule) {
            for (int[] interval : employee) {
                events.add(new Event(interval[0], 1));
                events.add(new Event(interval[1], -1));
            }
        }

        // 2. Sort all events
        Collections.sort(events);

        // 3. Sweep
        List<int[]> freeTime = new ArrayList<>();
        int activeCount = 0;
        Integer gapStart = null;

        for (Event event : events) {
            if (event.type == 1) {
                if (gapStart != null && activeCount == 0) {
                    if (event.time > gapStart) {
                        freeTime.add(new int[]{gapStart, event.time});
                    }
                }
                activeCount++;
            } else {
                activeCount--;
                if (activeCount == 0) {
                    gapStart = event.time;
                }
            }
        }
        return freeTime.toArray(new int[freeTime.size()][]);
    }

    public static void main(String[] args) {
        int[][][] schedule = {{{2,4},{7,10}},{{1,5}},{{6,9}}};
        System.out.println(Arrays.deepToString(employeeFreeTime(schedule)));
    }

}

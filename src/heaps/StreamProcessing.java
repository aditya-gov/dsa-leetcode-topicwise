package heaps;

import java.util.PriorityQueue;

/**
 * There are 3 kinds of queries on a stream of numbers:
 * 	(1, X) - Add X in your list of numbers
 * 	(2, X) - Add X to all the existing numbers
 * 	(3)    - Print the min number so far and remove it from the list
 *
 * 	For e.g.
 * 	(1, 3) - current list = [3]
 * 	(1, 5) - current list = [3, 5]
 * 	(2, 10) - add 10 to all the existing numbers, list becomes [13, 15]
 * 	(1, 12) - current list = [12, 13, 15]
 * 	(3)  - print the minimum number and remove it from the list: 12
 * Code in java
 */
public class StreamProcessing {

    public static int printMinimum(int[][] queries) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int offset = 0;
        int minVal = 0;
        for (int[] query : queries) {
            int type = query[0];
            if (type == 1) {
                int x = query[1];
                pq.add(x - offset);
            } else if (type == 2) {
                int x = query[1];
                offset += x;
            } else {
                if (!pq.isEmpty()) {
                    minVal = pq.poll() + offset;
                }
            }
        }
        return minVal;
    }

    public static void main(String[] args) {
        int[][] queries = {
                {1, 3},
                {1, 5},
                {2, 10},
                {1, 12},
                {3}
        };
        System.out.println(printMinimum(queries));
    }

}

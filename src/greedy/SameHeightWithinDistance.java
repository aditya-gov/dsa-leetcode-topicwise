package greedy;

import java.util.HashMap;
import java.util.Map;

public class SameHeightWithinDistance {

    /**
     * Checks if there are two buildings with the same height at most k distance apart.
     * @param heights The array of building heights.
     * @param k The maximum allowed distance.
     * @return true if such a pair exists, false otherwise.
     */
    public static boolean hasSameHeight(int[] heights, int k) {
        // A map to store the last seen index of each height
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];

            // Check if we've seen this height before
            if (lastSeen.containsKey(currentHeight)) {
                int lastIndex = lastSeen.get(currentHeight);

                // Calculate the distance
                int distance = i - lastIndex;

                // Check if the distance is at most k
                if (distance <= k) {
                    return true;
                }
            }
            // Update the map with the current index for the current height
            lastSeen.put(currentHeight, i);
        }

        return false;
    }

    public static void main(String[] args) {
        // Example 1: Pair exists within distance k
        int[] heights1 = {1, 2, 3, 4, 1, 5};
        int k1 = 4;
        System.out.println("Heights: {1, 2, 3, 4, 1, 5}, k = 4 -> " + hasSameHeight(heights1, k1)); // Expected: true

        // Example 2: Pair exists, but distance is greater than k
        int[] heights2 = {1, 2, 3, 4, 1, 5};
        int k2 = 3;
        System.out.println("Heights: {1, 2, 3, 4, 1, 5}, k = 3 -> " + hasSameHeight(heights2, k2)); // Expected: false

        // Example 3: No duplicate heights
        int[] heights3 = {1, 2, 3, 4, 5, 6};
        int k3 = 2;
        System.out.println("Heights: {1, 2, 3, 4, 5, 6}, k = 2 -> " + hasSameHeight(heights3, k3)); // Expected: false

        // Example 4: A more complex case
        int[] heights4 = {8, 1, 6, 2, 3, 5, 8, 4};
        int k4 = 6;
        System.out.println("Heights: {8, 1, 6, 2, 3, 5, 8, 4}, k = 6 -> " + hasSameHeight(heights4, k4)); // Expected: true
    }
}

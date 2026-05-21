package greedy;

import java.util.Arrays;

/**
 * Similar to Meeting Room 2 Leetcode
 */
public class MinimumPlatforms {

    public static int minPlatforms(int[] arrival, int[] departure) {
        int n = arrival.length;
        Arrays.sort(arrival); // ascending order
        Arrays.sort(departure); // ascending order
        int minPlatform = 1, res = 1;
        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arrival[i]  <= departure[j]) {
                minPlatform++;
                i++;
            } else {
                minPlatform--;
                j++;
            }
            if (minPlatform > res) {
                res = minPlatform;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arrival = {900, 940, 950, 1100, 1500, 1800};
        int[] departure = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(minPlatforms(arrival, departure));
    }

}

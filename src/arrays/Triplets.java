package arrays;

import java.util.Arrays;

public class Triplets {

    public static void main(String[] args) {
        Triplets triplets = new Triplets();
        int[] d = {1, 2, 3, 4, 5};
        int t = 8;
        System.out.println("No of triplets: " + triplets.triplets(d, t));
    }

    public long triplets(int[] d, int t) {
        int n = d.length;
        long count = 0;

        Arrays.sort(d);

        for (int j = 1; j < n - 1; j++) {
            int i = 0, k = n - 1;

            while (i < j && k > j) {
                // Ensure strictly increasing condition
                if (d[i] < d[j] && d[j] < d[k]) {
                    long sum = (long) d[i] + d[j] + d[k];

                    if (sum <= t) {
                        // Since d is sorted, all values of k from current k down to j+1 will also work
                        count += (k - j);
                        i++;  // try next i
                    } else {
                        k--;  // reduce sum
                    }
                } else {
                    if (d[i] >= d[j]) {
                        i++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return count;
    }
}

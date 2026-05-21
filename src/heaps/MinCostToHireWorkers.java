package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToHireWorkers {

    public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] ratio = new double[n][2];

        for (int i = 0; i < n; i++) {
            ratio[i][0] = (double) wage[i] / quality[i];
            ratio[i][1] = quality[i];
        }

        Arrays.sort(ratio, Comparator.comparingDouble(a -> a[0]));

        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double minCost = Double.MAX_VALUE;
        double qualitySum = 0.0;

        for (double[] r : ratio) {
            maxHeap.offer(r[1]);
            qualitySum += r[1];

            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }
            if (maxHeap.size() == k) {
                minCost = Math.min(minCost, qualitySum * r[0]);
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[] quality = {10,20,5};
        int[] wage = {70,50,30};
        int k = 2;
        System.out.println(minCostToHireWorkers(quality, wage, k));
    }

}

package dp.knapsack;

/**
 * Given the weights and values of n items, the task is to put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack, we can repeatedly put the same item and we can also
 * put a fraction of an item.
 */
public class UnboundedFractionalKnapsack {

    public float fractionalKnapsack(int W, float[] val, float[] wt) {
        int n = val.length;
        // maxRatio will store the maximum value to weight ratio we can have for any item
        float maxRatio = Integer.MIN_VALUE;
        // store the index of that element
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if ((val[i] / wt[i]) > maxRatio) {
                maxRatio = val[i] / wt[i];
                maxIdx = i;
            }
        }
        // The item with the maximum value to weight ratio will be put into the knapsack repeatedly until full
        return W * maxRatio;
    }

    public static void main(String[] args) {
        float[] val = {14, 27, 44, 19};
        float[] wt = {6, 7, 9, 8};
        int W = 50;
        UnboundedFractionalKnapsack unboundedFractionalKnapsack = new UnboundedFractionalKnapsack();
        System.out.printf("Fractional Knapsack: " + unboundedFractionalKnapsack.fractionalKnapsack(W, val, wt));
    }
}

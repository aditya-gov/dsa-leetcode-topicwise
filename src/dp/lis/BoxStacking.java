package dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxStacking {

    static class Box implements Comparable<Box> {

        int length;
        int width;
        int height;

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }

        @Override
        public int compareTo(Box b) {
            return Integer.compare(this.length * this.width, b.length * b.width);
        }
    }

    public static int maxStackHeight(int[] length, int[] width, int[] height) {
        int n = length.length;
        Box[] boxes = new Box[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            boxes[k++] = new Box(length[i], width[i], height[i]);
            boxes[k++] = new Box(width[i], height[i], length[i]);
            boxes[k++] = new Box(height[i], length[i], width[i]);
        }
        Arrays.sort(boxes);

        int[] dp = new int[3 * n];
        int maxHeight = 0;

        for (int i = k - 1; i >= 0; i--) {
            dp[i] = boxes[i].height;
            for (int j = i + 1; j < k; j++) {
                if (boxes[i].length < boxes[j].length && boxes[i].width < boxes[j].width) {
                    dp[i] = Math.max(dp[i], boxes[i].height + dp[j]);
                }
            }
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight;
    }

    public static int maxHeight(int[] length, int[] width, int[] height) {
        int n = height.length;
        List<int[]> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = length[i];
            int b = width[i];
            int c = height[i];
            boxes.add(new int[] {a, b, c});
            boxes.add(new int[] {a, c, b});
            boxes.add(new int[] {b, a, c});
            boxes.add(new int[] {b, c, a});
            boxes.add(new int[] {c, b, a});
            boxes.add(new int[] {c, a, b});
        }

        boxes.sort((x, y) -> y[0] != x[0] ? y[0] - x[0] : y[1] - x[1]);
        int size = boxes.size();
        int[] dp = new int[size];
        int res = 0;

        for (int i = size - 1; i >= 0; i--) {
            dp[i] = boxes.get(i)[2]; // height
            for (int j = i + 1; j < size; j++) {
                // comparison of length & width as base
                if (boxes.get(i)[0] > boxes.get(j)[0] && boxes.get(i)[1] > boxes.get(j)[1]) {
                    dp[i] = Math.max(dp[i], boxes.get(i)[2] + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] length = { 4, 1, 4, 10 };
        int[] width = { 6, 2, 5, 12 };
        int[] height = { 7, 3, 6, 32 };
        //System.out.println(maxStackHeight(length, width, height));
        System.out.println(maxHeight(length, width, height));
    }

}

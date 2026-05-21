package arrays;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubArrayKDistinct {

    public static int smallestSubArrayWithKDistinct(int[] arr, int k) {
        int n = arr.length;
        int start = 0, end = n;
        int i = 0, j = 0;
        Map<Integer, Integer> hm = new HashMap<>();
        while (j < n) {
            hm.put(arr[j], hm.getOrDefault(arr[j], 0) + 1);
            j++;
            while (hm.size() == k) {
                int windowLen = (j - 1) - (i - 1);
                int subArrayLen = end - start + 1;
                if (windowLen < subArrayLen) {
                    start = i;
                    end = j - 1;
                }
                if (hm.get(arr[i]) == 1) {
                    hm.remove(arr[i]);
                } else {
                    hm.put(arr[i], hm.get(arr[i]) - 1);
                }
                i++;
            }
        }
        if (start == 0 && end == n) {
            return -1;
        }
        return end - start + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 2, 2, 3, 3, 4, 5};
        int k = 3;
        System.out.println(smallestSubArrayWithKDistinct(arr, k));
    }

}

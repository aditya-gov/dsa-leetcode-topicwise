package BinarySearch;

import java.util.Arrays;

public class AllocateMinimumPages {

    public static int findPages(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
        }
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(arr, k, mid)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    private static boolean check(int[] arr, int k, int pageLimit) {
        int count = 1;
        int pageSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (pageSum + arr[i] > pageLimit) {
                count++;
                pageSum = arr[i];
            } else {
                pageSum += arr[i];
            }
        }
        return count <= k;
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 67, 90};
        int k = 2;
        System.out.println(findPages(arr, k));
    }
}

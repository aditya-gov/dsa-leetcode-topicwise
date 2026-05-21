package BinarySearch;

public class FindKthMissing {

    public static int findKthMissingPositive(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //The expression arr[i] - (i+1) calculates the number of missing elements because it compares
            // the value that should be at index i with the value that is at index i.
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,7,11};
        int k = 5;
        System.out.println(findKthMissingPositive(arr, k));
    }
}

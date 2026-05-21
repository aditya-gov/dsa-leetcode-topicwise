package arrays;

/**
 * Quick Select Algorithm
 */
public class KthSmallestQuickSelect {

    public static int kthSmallest(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;
        return kthSmallestUtil(arr, low, high, k);
    }

    private static int kthSmallestUtil(int[] arr, int low, int high, int k) {
        int partition = partition(arr, low, high);
        // if partition value is equal to the kth position, return value at k.
        if (partition == k - 1) {
            return arr[partition];
            // if partition value is less than kth position, search right side of the array.
        } else if (partition < k - 1) {
            return kthSmallestUtil(arr, partition + 1, high, k);
            // if partition value is more than kth position, search left side of the array.
        } else {
            return kthSmallestUtil(arr, low, partition - 1, k);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int pivotLocation = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value to the left of the pivot location
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotLocation];
                arr[pivotLocation] = temp;
                pivotLocation++;
            }
        }
        // swapping pivot to the final pivot location
        int temp = arr[high];
        arr[high] = arr[pivotLocation];
        arr[pivotLocation] = temp;
        return pivotLocation;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 10, 4, 5, 8, 6, 11, 26 };
        int k = 3;
        System.out.println(kthSmallest(arr, k));
    }
}

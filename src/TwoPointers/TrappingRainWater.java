package TwoPointers;

public class TrappingRainWater {

    public static int maxWater(int[] arr) {
        int left = 1;
        int right = arr.length - 2;
        int lMax = arr[left - 1];
        int rMax = arr[right + 1];

        int res = 0;
        while (left <= right) {
            // If rMax is smaller, then we can decide the amount of water for arr[right]
            if (rMax <= lMax) {
                // Add the water for arr[right]
                res += Math.max(0, rMax - arr[right]);
                // Update right max
                rMax = Math.max(rMax, arr[right]);
                // Update right pointer as we have decided the amount of water for this
                right--;
            } else {
                // Add the water for arr[left]
                res += Math.max(0, lMax - arr[left]);
                // Update left max
                lMax = Math.max(lMax, arr[left]);
                // Update left pointer as we have decided the amount of water for this
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 1, 0, 4};
        System.out.println(maxWater(arr));
    }
}

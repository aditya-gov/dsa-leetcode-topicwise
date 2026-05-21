package BinarySearch;

public class KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 0;
        for (int pile : piles) {
            if (pile > high) {
                high = pile;
            }
        }
        int minSpeed = high;
        while (low <= high) {
            int k = low + (high - low) / 2;
            long totalHrs = 0;
            for (int pile : piles) {
                totalHrs += (pile - 1) / k + 1;
            }
            if (totalHrs <= h) {
                minSpeed = k;
                high = k - 1;
            } else {
                low = k + 1;
            }
        }
        return minSpeed;
    }

    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int h = 5;
        System.out.println(minEatingSpeed(piles, h));
    }
}

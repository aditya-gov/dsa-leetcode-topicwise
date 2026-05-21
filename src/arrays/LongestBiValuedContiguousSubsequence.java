package arrays;

public class LongestBiValuedContiguousSubsequence {

    public static int countLongestBiValuedContiguousSubsequence(int[] A) {
        int lastVisited = -1;
        int secondLastVisited = -1;
        int res = 0;
        int count = 0;
        int lastVisitedRepeatedCount = 0;

        for (int arr : A) {
            if (arr == lastVisited || arr == secondLastVisited) {
                count++;
            } else {
                count = lastVisitedRepeatedCount + 1;
            }
            if (arr == lastVisited) {
                lastVisitedRepeatedCount++;
            } else {
                lastVisitedRepeatedCount = 1;
                secondLastVisited = lastVisited;
                lastVisited = arr;
            }
            res = Math.max(count, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {0, 5, 4, 4, 5, 12};
        //int[] arr = new int[] {1, 2, 3, 2};
        System.out.println(countLongestBiValuedContiguousSubsequence(arr));
    }

}

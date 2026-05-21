package greedy;

public class JumpGame {

    public static int minJumps(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int currEnd = 0, currFar = 0;
        for (int i = 0; i < n - 1; i++) {
            currFar = Math.max(i + nums[i], currFar);
            if (i == currEnd) {
                currEnd = currFar;
                jumps++;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(minJumps(nums));
    }

}

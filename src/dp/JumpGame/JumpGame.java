package dp.JumpGame;

public class JumpGame {

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int farIndex = 0;
        for (int i = 0; i < n; i++) {
            if (farIndex < i) {
                return false;
            }
            farIndex = Math.max(i + nums[i], farIndex);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
    }

}

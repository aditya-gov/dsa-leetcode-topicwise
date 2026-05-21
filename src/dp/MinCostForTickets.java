package dp;

import java.util.HashSet;
import java.util.Set;

public class MinCostForTickets {

    public static int minCostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        Set<Integer> travelDays = new HashSet<>();
        for (int day : days) {
            travelDays.add(day);
        }
        for (int i = 1; i <= lastDay; i++) {
            // If we don't travel today, the minimum cost is the same as yesterday
            if (!travelDays.contains(i)) {
                dp[i] = dp[i - 1];
                continue;
            }
            int buy1Day = dp[i - 1] + costs[0];
            int buy7Day = dp[Math.max(0, i - 7)] + costs[1];
            int buy30Day = dp[Math.max(0, i - 30)] + costs[2];

            dp[i] = Math.min(buy1Day, Math.min(buy7Day, buy30Day));
        }
        return dp[lastDay];
    }

    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println(minCostTickets(days, costs));
    }
}

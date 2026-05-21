package greedy;

import java.util.*;

public class MaximumTrains {

    public static int maxTrainsCanBeStopped(int n, int m, List<List<Integer>> trains) {
        int[][] res = new int[m][4];

        for(int i = 0; i < m; i++) {
            res[i][0] = i;
            res[i][1] = trains.get(i).get(0); // arrival
            res[i][2] = trains.get(i).get(1); // departure
            res[i][3] = trains.get(i).get(2); // platform
        }

        Arrays.sort(res, Comparator.comparingDouble(o -> o[2])); // sort in ascending order of departure
        int count = 0;
        int[][] ans = new int[n][2];

        for(int i = 0; i < m; i++) {
            int pn = res[i][3] - 1; // platform
            if(ans[pn][1] <= res[i][1]) { // previous departure <= current arrival
                count++;
                ans[pn][0] = res[i][1]; // arrival
                ans[pn][1] = res[i][2]; // departure
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arrival = {1000,1010,1025,1130,1130};
        int[] departure = {1030,1020,1040,1145,1140};
        int[] platform = {1,1,1,2,2};
        List<List<Integer>> trains = new ArrayList<>();
        for (int i = 0; i < arrival.length; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(arrival[i]);
            temp.add(departure[i]);
            temp.add(platform[i]);
            trains.add(temp);
        }
        int m = 5; // trains
        int n = 2; // platforms
        System.out.println(maxTrainsCanBeStopped(n, m, trains));
    }

}

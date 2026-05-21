package dp.lis;

import java.util.Arrays;
import java.util.Comparator;

public class BuildingBridges {

    static class CityPairs {
        int north, south;
        CityPairs(int north, int south) {
            this.north = north;
            this.south = south;
        }
    }

    static class CustomComparator implements Comparator<CityPairs> {

        @Override
        public int compare(CityPairs o1, CityPairs o2) {
            if (o1.north == o2.north) {
                return o1.south - o2.south;
            }
            return o1.north - o2.north;
        }
    }

    public static int maxBridges(CityPairs[] pairs, int n) {
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
        Arrays.sort(pairs, new CustomComparator());
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i].south >= pairs[j].south) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        int max = lis[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, lis[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 4;
        CityPairs[] pairs = new CityPairs[n];
        pairs[0] = new CityPairs(6, 2);
        pairs[1] = new CityPairs(4, 3);
        pairs[2] = new CityPairs(2, 6);
        pairs[3] = new CityPairs(1, 5);
        System.out.println(maxBridges(pairs, n));
    }

}

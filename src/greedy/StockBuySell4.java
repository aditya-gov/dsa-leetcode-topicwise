package greedy;

public class StockBuySell4 {

    public static int maxProfit(int[] prices, int k) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int[] buyingPrice = new int[k];
        int[] profit = new int[k];
        for (int i = 0; i < k; i++) {
            buyingPrice[i] = Integer.MAX_VALUE;
            profit[i] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            for (int i = 0; i < k; i++) {
                buyingPrice[i] = Math.min(buyingPrice[i], price - (i > 0 ? profit[i - 1] : 0));
                profit[i] = Math.max(profit[i], price - buyingPrice[i]);
            }
        }
        return profit[k - 1];
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
        System.out.println(maxProfit(prices, k));
    }

}

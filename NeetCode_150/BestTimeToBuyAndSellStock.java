class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int left = 0;
        for (int right = 0; right < prices.length; right++) {
            max = Math.max(max, prices[right] - prices[left]);
            if (prices[right] < prices[left]) {
                left = right;
            }
        }
        return max;
    }
}

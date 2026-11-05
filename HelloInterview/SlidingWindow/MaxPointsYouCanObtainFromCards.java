/*
public class Solution {
    public int maxScore(int[] cards, int k) {
        int[] newCards = new int[cards.length + k - 1];
        System.arraycopy(cards, 0, newCards, 0, cards.length);
        for (int i = 0; i < k - 1; i++) {
            newCards[cards.length + i] = cards[i];
        }

        int left = 0;
        int sum = 0;
        int res = Integer.MIN_VALUE;
        for (int right = 0; right < newCards.length; right++) {
            sum += newCards[right];
            if (right - left + 1 == k) {
                res = Math.max(res, sum);
                sum -= newCards[left];
                left++;
            }
        }
        return res;
    }
}
*/


public class Solution {
    public int maxScore(int[] cards, int k) {
        // Your code goes here
        int total = 0;
        for (int card : cards) {
            total += card;
        }
        int windowSize = cards.length - k;
        if (windowSize == 0) return total;
        int left = 0;
        int sum = 0;
        int res = Integer.MIN_VALUE;
        for (int right = 0; right < cards.length; right++) {
            sum += cards[right];
            if (right - left + 1 == windowSize) {
                res = Math.max(res, total - sum);
                sum -= cards[left];
                left++;
            }
        }
        return res;
    }
}
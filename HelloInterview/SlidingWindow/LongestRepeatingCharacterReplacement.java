public class Solution {
    public int characterReplacement(String s, int k) {
        char[] charArray = s.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        int res = 0;
        int maxFreq = 0;
        int left = 0;
        for (int right = 0; right < charArray.length; right++) {
            freq.put(charArray[right], freq.getOrDefault(charArray[right], 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(charArray[right]));

            while (right - left + 1 - maxFreq > k) {
                freq.put(charArray[left], freq.get(charArray[left]) -1);
                if (freq.get(charArray[left]) == 0) {
                    freq.remove(charArray[left]);
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
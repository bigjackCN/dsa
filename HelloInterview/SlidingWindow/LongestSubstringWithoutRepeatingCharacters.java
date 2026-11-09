public class Solution {
    public int longestSubstringWithoutRepeat(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> freq = new HashMap<>();
        char[] charArray = s.toCharArray();
        int res = 0;
        int left = 0;
        for (int right = 0; right < charArray.length; right++) {
            freq.put(charArray[right], freq.getOrDefault(charArray[right], 0) + 1);

            while (right - left + 1 > freq.size()) {
                freq.put(charArray[left], freq.get(charArray[left]) - 1);
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
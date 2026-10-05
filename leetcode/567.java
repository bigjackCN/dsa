/*
567. Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of
s1, or false otherwise. In other words, return true if one of s1's
permutations is a substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:
1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.

Approach: Fixed-size sliding window. A permutation of s1 is any string with
the same letter counts, so we look for a window of length s1.length() in s2
whose int[26] letter counts equal s1's counts. Pre-fill the first m-1
characters, then for each right: add s2[right], compare the two count
arrays, then remove s2[left] and advance left. Each slide is O(1) because
only one letter enters and one leaves; comparing two int[26] arrays is
O(26) = O(1).

Common mistake: treating "permutation" as "same letters in order with gaps
allowed" (a subsequence check). A permutation means same letters in ANY
order, and "substring" means the chunk must be contiguous.

Brute force (recount every window): O(m * n).

Time: O(n) where n = s2.length()
Space: O(1) (two fixed 26-entry arrays)
*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] char1Count = new int[26];
        int[] char2Count = new int[26];

        for (int i = 0; i < c1.length; i++) {
            char1Count[c1[i] - 'a']++;
        }
        for (int i = 0; i < c1.length - 1; i++) {
            char2Count[c2[i] - 'a']++;
        }

        int left = 0;
        for (int right = c1.length - 1; right < c2.length; right++) {
            char2Count[c2[right] - 'a']++;
            if (Arrays.equals(char1Count, char2Count)) return true;
            char2Count[c2[left] - 'a']--;
            left++;
        }
        return false;
    }
}

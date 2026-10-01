/*
3. Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. Note that "pwke" is a subsequence, not a substring.

Constraints:
0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.

Approach: Sliding window with a HashSet tracking the characters currently in the
window. Expand the window from the right; when a duplicate is found, shrink from
the left until the duplicate is removed. Each character is added/removed from the
window at most once overall, so despite the nested loop this runs in O(n) time.

Time: O(n)
Space: O(min(n, charset size))
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int max = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(c);
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}

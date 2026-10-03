/*
424. Longest Repeating Character Replacement

You are given a string s consisting of only uppercase English letters and
an integer k. You can choose up to k characters of the string and replace
them with any other uppercase English letter. After performing at most k
replacements, return the length of the longest substring containing all
the same letter.

Example 1:
Input: s = "XYYX", k = 2
Output: 4
Explanation: Either replace the 'X's with 'Y's, or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace one 'A' at index 3 so that s = "AABBBBA".

Approach: Template A sliding window (longest window, shrink while
invalid). Track a count[26] frequency array for the window plus a running
maxFreq (the count of whichever character is currently most common in the
window). A window of length L is valid when L - maxFreq <= k, i.e. the
number of "other" characters you'd need to replace is within budget.

Key trick: maxFreq is only ever updated upward (when adding a character on
the right) and is never recomputed/decreased when shrinking from the left.
This means it can go stale (stay higher than the window's true current
max). That's fine: a stale maxFreq only makes the while condition trigger
less often, i.e. it can only cause you to under-shrink slightly, never to
accept a window that isn't backed by some actual valid window seen earlier.
Since we only care about the longest valid length overall, the final
answer is unaffected. This is a useful trick in general: for "longest
window" problems, a monotonically-growing bound on the window's internal
state is often good enough -- you don't always need it to be exact at
every step.

Time: O(n)
Space: O(1) (fixed 26-entry array)
*/

class Solution {
    public int characterReplacement(String s, int k) {
        char[] list = s.toCharArray();
        int[] characterCount = new int[26];
        int maxFreq = 0;
        int left = 0;
        int res = 0;

        for (int right = 0; right < list.length; right++) {
            int charIndex = list[right] - 'A';
            characterCount[charIndex]++;
            maxFreq = Math.max(maxFreq, characterCount[charIndex]);

            while (right - left + 1 - maxFreq > k) {
                int leftIndex = list[left] - 'A';
                characterCount[leftIndex]--;
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}

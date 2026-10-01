/*
209. Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target,
return the minimal length of a contiguous subarray [numsl, ..., numsr] of
which the sum is greater than or equal to target. If there is no such
subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Approach: Sliding window tracking a running sum. Grow the window from the
right by adding nums[right]. While the window sum is still >= target,
record the window length (it's valid), then shrink from the left. Because
the check happens as the FIRST thing inside the while loop (before removing
anything further), every valid window length encountered during shrinking
gets recorded -- not just the state right before or right after shrinking.

Common bug: checking validity only once before the while loop, or only
once after it exits, instead of on every iteration of the shrink itself.
Since positive integers can make the sum drop by more than 1 in a single
removal, a valid window can exist mid-shrink and get skipped entirely if
you don't check at every step.

Time: O(n)
Space: O(1)
*/

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, acc = 0;
        int min = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            acc += nums[right];

            while (acc >= target) {
                min = Math.min(min, right - left + 1);
                acc -= nums[left];
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

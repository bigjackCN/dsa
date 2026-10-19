/*
34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the
starting and ending position of a given target value. If target is not found
in the array, return [-1, -1]. You must write an algorithm with O(log n)
runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]

Constraints:
0 <= nums.length <= 10^5
-10^9 <= nums[i], target <= 10^9
nums is non-decreasing (duplicates allowed).

Approach: Two binary searches with inclusive bounds. A match at mid is not
necessarily the first or last copy, so on a match we REMEMBER it and keep
searching in the direction where a better answer could be:
  - first occurrence: first = mid; right = mid - 1  (keep looking left)
  - last occurrence:  last = mid;  left = mid + 1   (keep looking right)
The candidates start at -1, so an absent target (or an empty array, where the
loops never run) naturally returns [-1, -1]. One loop cannot do both because
the two searches move in opposite directions on a match.

Mistakes made while solving:
- First attempt scanned linearly (left++ / right--) after a match: O(n) in
  the worst case, e.g. [1,2,3,4,8,9,10,11,12] with target 8.
- Compile slips: missing `new` in `new int[]{...}`, mismatched bracket
  `{first, last]`, wrong method name/return type copied from the last problem.
- Tried to find both ends in a single loop (and read nums[mid + 1], which can
  go out of bounds). Use two separate searches.

Complexity: Time O(log n) - two searches, each at most log2(n) iterations.
            Space O(1).
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;

        // first search: remember the match, keep going left
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                first = mid;
                right = mid - 1;
            }
        }

        // second search: remember the match, keep going right
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                last = mid;
                left = mid + 1;
            }
        }
        return new int[]{first, last};
    }
}

/*
33. Search in Rotated Sorted Array

An integer array nums was sorted in ascending order (with distinct values)
and then possibly rotated at an unknown pivot, e.g. [0,1,2,4,5,6,7] becomes
[4,5,6,7,0,1,2]. Given nums and an integer target, return the index of
target, or -1 if it is not in nums. You must write an algorithm with
O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

Constraints:
1 <= nums.length <= 5000
-10^4 <= nums[i], target <= 10^4
All values are unique.

Approach: Binary search with inclusive bounds. After picking mid, at least
one half is always sorted:
  - nums[mid] < nums[right]: no rotation drop can sit between mid and right,
    so [mid..right] is ascending.
  - otherwise [left..mid] is ascending.
Check whether target falls inside the sorted half's value range. If yes,
search that half; if no, it can only be in the other half. Because
nums[mid] != target was already checked, mid itself can be discarded
(mid - 1 / mid + 1).

Mistakes made while solving:
- Initially described "rotated vs not rotated" cases; the better framing is
  "which half is sorted at this mid".
- Skipped the plan/complexity before coding (state it first).
- Explained mid +/- 1 when asked why `<` picks the right half; the real
  reason is that a rotation drop would make nums[mid] > nums[right].

Follow-up (LC 81, duplicates): when nums[mid] == nums[right] we cannot tell
which half is sorted (e.g. [1,0,1,1,1]), so shrink with right-- instead.
Worst case becomes O(n).

Complexity: Time O(log n) - the range halves every iteration.
            Space O(1).
*/

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {    // mid..right is sorted
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {    // left..mid is sorted
                if (target < nums[left] || target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

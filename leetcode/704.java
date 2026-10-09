/*
704. Binary Search

Given an array of integers nums which is sorted in ascending order, and an
integer target, write a function to search target in nums. If target
exists, return its index. Otherwise, return -1. You must write an algorithm
with O(log n) runtime complexity.

Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4

Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1

Constraints:
1 <= nums.length <= 10^4
-10^4 < nums[i], target < 10^4
All the integers in nums are unique, sorted in ascending order.

Approach: Keep the search range [left, right] (both ends inclusive). Look at
the middle element: if it equals target, return it; if it is larger, the
target can only be to the left, so right = mid - 1; otherwise left = mid + 1.
Each step halves the range. If the range becomes empty, return -1.

Pairing rule (the part that goes wrong in interviews): the three choices
must be consistent.
  Inclusive bounds:  right = n - 1;  while (left <= right);
                     right = mid - 1; left = mid + 1.
  Exclusive upper:   right = n;      while (left < right);
                     right = mid;     left = mid + 1.
Mixing them (e.g. inclusive bounds with while (left < right)) skips the last
remaining element.

Also: mid = left + (right - left) / 2 avoids integer overflow compared with
(left + right) / 2.

Mistakes made while solving:
- while (left < right) with inclusive bounds: missed single-element ranges
  ([5] with target 5 returned -1; [1,3] with target 3 returned -1).
- Returning left at the end instead of -1.

Time: O(log n)
Space: O(1)
*/

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

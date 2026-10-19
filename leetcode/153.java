/*
153. Find Minimum in Rotated Sorted Array

An array of length n sorted in ascending order was rotated between 1 and n
times. Given the rotated array nums of unique elements, return the minimum
element. You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0

Example 3:
Input: nums = [11,13,15,17]
Output: 11

Constraints:
n == nums.length, 1 <= n <= 5000
-5000 <= nums[i] <= 5000
All integers are unique; nums is sorted and rotated 1..n times.

Approach: Binary search for the rotation drop, exclusive-style template
(left < right, right = mid). Each iteration:
  - If nums[left] < nums[right] the window is already ascending, so
    nums[left] is the minimum.
  - Otherwise nums[left] > nums[right] and the drop is inside the window.
    If nums[left] <= nums[mid], then nums[mid] >= nums[left] > nums[right],
    so mid is not the minimum and the minimum is strictly right of mid:
    left = mid + 1.
  - Else nums[mid] < nums[left]: the drop is at or before mid and mid itself
    may be the minimum, so right = mid (NOT mid - 1).
When left == right, that single element is the minimum.

Why `<=` and not `<`: when left == mid (window of 2, e.g. [2,1]) the strict
version falls into the else branch, sets right = mid = left and returns 2.
Simpler alternative: compare nums[mid] with nums[right] only:
  if (nums[mid] > nums[right]) left = mid + 1; else right = mid;

Mistakes made while solving:
- Compared nums[left] with nums[mid] without an "already sorted" case, which
  fails on [11,13,15,17].
- Paired right = mid with while (left <= right) (pairing rule from LC 704:
  right = mid needs left < right).
- left = mid - 1 (moves backwards, infinite loop on [3,4,5,1,2]); typo
  num[mid]; returned an index instead of the value; strict < broke [2,1].
- Intermediate trace values were wrong (mid + 1 computed as mid - 1); keep an
  index table and recompute every row.

Complexity: Time O(log n) - at most log2(n) iterations, the window halves.
            Space O(1).
*/

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {    // minimum is in (mid, right]
                left = mid + 1;
            } else {    // minimum is in [left, mid]
                right = mid;
            }
        }
        return nums[right];
    }
}

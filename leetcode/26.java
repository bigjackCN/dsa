/*
26. Remove Duplicates from Sorted Array

Given an integer array nums sorted in non-decreasing order, remove the
duplicates in place such that each unique element appears only once. The
relative order of the elements should be kept the same. Return k, the
number of unique elements. The first k elements of nums must hold the
unique elements in their original order; what remains beyond k does not
matter. Use only constant extra space.

Example 1:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]

Example 2:
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]

Constraints:
1 <= nums.length <= 3 * 10^4
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.

Approach: Same-direction two pointers (read / write).
- write = index of the last confirmed unique value (the end of the output).
- read scans every element once.
- If nums[read] differs from nums[write], it's a new value: write++ and
  copy it to nums[write]. Return write + 1 at the end.
Comparing with nums[write] works because the array is sorted: nums[write]
is always the largest unique value so far, so anything different is new.

First idea (valid but slower): when a duplicate is found, shift the rest
of the array left. That is O(n^2) in the worst case (e.g. all elements
equal). The read/write pointers place each unique value at its final slot
exactly once.

Follow-up (LC 80, at most 2 copies): start write = 2, read = 2, and copy
only if nums[read] != nums[write - 2]. Compare with the element two slots
back in the OUTPUT. Generalizes to "at most k": compare with nums[write - k].

Time: O(n)
Space: O(1)
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int write = 0;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] == nums[write]) {
                continue;
            }
            write++;
            nums[write] = nums[read];
        }
        return write + 1;
    }
}

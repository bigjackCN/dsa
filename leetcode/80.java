/*
80. Remove Duplicates from Sorted Array II

Given an integer array nums sorted in non-decreasing order, remove some
duplicates in place such that each unique element appears at most twice.
The relative order of the elements should be kept the same. Return k, the
number of elements in the final result; the first k elements of nums must
hold it. Use only constant extra space.

Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]

Example 2:
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]

Constraints:
1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order.

Approach: Read/write pointers (see LC 26). The first two elements are
always kept, so write = read = 2. Keep nums[read] only if it differs from
nums[write - 2], the second-to-last element KEPT SO FAR. Because the array
is sorted, if nums[read] equals nums[write - 2] then the kept element at
write - 1 (between them in sorted order) is the same value too, so two
copies are already in the output.

Note: write moves only when an element is kept; read moves every step.
Arrays of length 1 or 2 are returned as-is (the length is NOT guaranteed
to be at least 2).

Generalizes to "at most k copies": start write = read = k and compare with
nums[write - k].

Time: O(n)
Space: O(1)
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) return nums.length;
        int write = 2;
        for (int read = 2; read < nums.length; read++) {
            if (nums[read] == nums[write - 2]) {
                continue;
            }
            nums[write] = nums[read];
            write++;
        }
        return write;
    }
}

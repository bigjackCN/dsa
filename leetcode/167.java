/*
167. Two Sum II - Input Array Is Sorted

Given a 1-indexed array of integers numbers that is already sorted in
non-decreasing order, find two numbers such that they add up to a specific
target. Return the indices of the two numbers, index1 and index2, as an
array [index1, index2] with 1 <= index1 < index2 <= numbers.length.
Exactly one solution exists, and you may not use the same element twice.
Your solution must use only constant extra space.

Example 1:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]

Example 2:
Input: numbers = [2,3,4], target = 6
Output: [1,3]

Example 3:
Input: numbers = [-1,0], target = -1
Output: [1,2]

Approach: Opposite-ends two pointers. left starts at the smallest value,
right at the largest. Compare sum to target:
- sum < target: numbers[left] is too small even when paired with the
  LARGEST remaining value, so it can't be part of the answer. Discard it
  (left++).
- sum > target: numbers[right] is too large even when paired with the
  SMALLEST remaining value. Discard it (right--).
- sum == target: done.
Each step discards one element, so at most n - 1 steps.

The correctness argument rests on the array being SORTED, not on the
answer being unique.

Common mistake: returning 0-indexed positions when the problem is
1-indexed (return left + 1, right + 1).

vs. LC 1 (HashMap): same O(n) time, but O(n) space. Sorted input lets us
trade the map for two pointers and get O(1) space.

Time: O(n)
Space: O(1)
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}

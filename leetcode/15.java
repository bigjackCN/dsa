/*
15. 3Sum

Given an integer array nums, return all the triplets
[nums[i], nums[j], nums[k]] such that i != j, i != k, j != k, and
nums[i] + nums[j] + nums[k] == 0. The solution set must not contain
duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:
Input: nums = [0,1,1]
Output: []

Example 3:
Input: nums = [0,0,0]
Output: [[0,0,0]]

Constraints:
3 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5

Approach: Sort, then fix nums[i] and run the Two Sum II opposite-ends
two pointers (LC 167) on nums[i+1 .. n-1], looking for a pair summing to
-nums[i].

Avoiding duplicates WITHOUT a Set (this is the part interviewers care about):
1. Outer loop: skip nums[i] if it equals nums[i-1]. Compare with the
   PREVIOUS element, not the next: the earlier copy already explored every
   triplet that can start with that value (including ones that reuse the
   same value, like [-1,-1,2]). Comparing with the next would wrongly skip
   the first copy.
2. Inner loop: after recording a triplet, skip duplicates of nums[left]
   and nums[right], THEN move both pointers once more (left++, right--).
   The skip loops only land ON the last copy of a duplicate run; the extra
   step moves past it. Forgetting that step causes an infinite loop.

Mistakes made while solving:
- Recording indices (i, left, right) instead of values.
- Putting the duplicate check after the search instead of before it.
- Starting left at 0 instead of i + 1.
- Skip loops that stop on the last duplicate but never advance past it.

Time: O(n^2)  (O(n log n) sort + n passes of an O(n) two-pointer sweep)
Space: O(1) extra, excluding the output (sort uses O(log n) stack)
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left + 1 < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right - 1 > left && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

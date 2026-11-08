public class Solution {
    public long maxSum(int[] nums, int k) {
        // Your code goes here
        long sum = 0;
        long max = Long.MIN_VALUE; 
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            if (right - left + 1 == k) {
                if (freq.size() == k) {
                    max = Math.max(max, sum);
                }
                freq.put(nums[left], freq.getOrDefault(nums[left], 0) - 1);
                sum -= nums[left];
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }
        }
        return max == Long.MIN_VALUE ? 0 : max;
    }
}
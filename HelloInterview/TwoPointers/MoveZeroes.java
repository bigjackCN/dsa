public class Solution {
    public void moveZeroes(int[] nums) {
        // Your code goes here
        int toInsert = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[toInsert];
                nums[toInsert] = tmp;
                toInsert++;
            }
        }
    }
}
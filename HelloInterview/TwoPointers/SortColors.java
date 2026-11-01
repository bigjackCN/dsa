/*
public class Solution {
    public void sortColors(int[] nums) {
        // Your code goes here
        // sort 0 first
        int toSort = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int tmp = nums[toSort];
                nums[toSort] = nums[i];
                nums[i] = tmp;
                toSort++;
            }
        }
        // sort 1 
        for (int i = toSort; i < nums.length; i++) {
            if (nums[i] == 1) {
                int tmp = nums[toSort];
                nums[toSort] = nums[i];
                nums[i] = tmp;
                toSort++;
            }
        }
        // no need to sort for 2
    }
}
*/

public class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;

        while (i <= right) {
            if (nums[i] == 0) {
                int tmp = nums[left];
                nums[left] = nums[i];
                nums[i] = tmp;
                left++;
                i++;
            } else if (nums[i] == 2) {
                int tmp = nums[right];
                nums[right] = nums[i];
                nums[i] = tmp;
                right--;
            } else {
                i++;
            }
        }
    }
}
/*
public class Solution {
    public int trappingWater(int[] height) {
        // Your code goes here
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int count = 0;

        while (left < right) {
            if (height[left] > height[right]) {
                rightMax = Math.max(rightMax, height[right]);
                count += rightMax - height[right];
                right--;
            } else {
                leftMax = Math.max(leftMax, height[left]);
                count += leftMax - height[left];
                left++;
            }
        }
        return count;
    }
}
*/

public class Solution {
    public int trappingWater(int[] height) {
        // Your code goes here
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int count = 0;
        while (left < right) {
            if (leftMax > rightMax) {
                right--;
                if (height[right] > rightMax) rightMax = height[right];
                count += rightMax - height[right];
            } else {
                left++;
                if (height[left] > leftMax) leftMax = height[left];
                count += leftMax - height[left];
            }
        }
        return count;
    }
}
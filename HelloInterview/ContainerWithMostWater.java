public class Solution {
    public int max_area(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;
        while (left < right) {
            int width = right - left;
            maxArea = Math.max(maxArea, Math.min(heights[left], heights[right]) * width);
            if (heights[left] > heights[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}
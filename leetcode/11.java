/*
11. Container With Most Water

You are given an integer array height of length n. There are n vertical
lines drawn such that the two endpoints of the i-th line are (i, 0) and
(i, height[i]). Find two lines that, together with the x-axis, form a
container that holds the most water. Return the maximum amount of water a
container can store. You may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: Lines at index 1 (height 8) and index 8 (height 7):
min(8, 7) * (8 - 1) = 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
2 <= n <= 10^5
0 <= height[i] <= 10^4

Approach: Opposite-ends two pointers. Start with the widest container
(left = 0, right = n - 1), record area = (right - left) * min(h[left], h[right]),
then move the pointer at the SHORTER line inward.

Why moving the shorter line is safe: say h[left] < h[right]. Any container
that uses left with some line j strictly inside (j < right) has a SMALLER
width and a height capped by h[left] (left is the short side), so its area
is at most h[left] * (smaller width) < the area we just recorded. So left
can never be part of a better container and can be discarded. Moving the
taller side can't help either: width shrinks and height is still capped by
the shorter line. If heights are equal, moving either pointer is safe.

Time: O(n) (each step discards one line: exactly n - 1 iterations)
Space: O(1)
*/

class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            max = Math.max(max, width * h);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}

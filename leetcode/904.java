/*
904. Fruit Into Baskets

You are visiting a farm with a single row of fruit trees arranged left to
right. fruits[i] is the type of fruit the i-th tree produces. You have two
baskets, each holding only one type of fruit (unlimited quantity). Starting
from any tree, you must pick exactly one fruit from every tree (including
the start tree) while moving right; stop when you reach a tree whose fruit
cannot fit in your baskets. Return the maximum number of fruits you can pick.

Example 1:
Input: fruits = [1,2,1]
Output: 3

Example 2:
Input: fruits = [0,1,2,2]
Output: 3
Explanation: Pick from trees [1,2,2].

Example 3:
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: Pick from trees [2,3,2,2].

Constraints:
1 <= fruits.length <= 10^5
0 <= fruits[i] < fruits.length

Restated: find the longest subarray containing at most 2 distinct values.

Approach: Template A sliding window (longest window, shrink while invalid).
Track counts per fruit type in a HashMap. The window is invalid when
map.size() > 2. Shrink from the left: decrement the count of fruits[left],
and REMOVE the key when its count hits 0 (otherwise size() never drops).
Record right - left + 1 after the shrink loop.

Why a map and not a set: when shrinking, a type may still appear elsewhere
in the window, so you need counts to know when a type has fully left.

Common mistakes seen while solving:
- Reusing the LC 424 condition (window length - maxFreq). That limits the
  number of "other" characters, not the number of distinct types.
- Writing the decremented count back with put() AFTER removing the key at
  zero, which re-inserts the key with count 0 and breaks size().
- Computing the decremented value but never writing it back to the map.

Time: O(n) (each index added once, removed at most once)
Space: O(1) (the map holds at most 3 entries)
*/

class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> types = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int right = 0; right < fruits.length; right++) {
            int type = fruits[right];
            types.put(type, types.getOrDefault(type, 0) + 1);
            while (types.size() > 2) {
                type = fruits[left];
                int value = types.get(type) - 1;
                if (value == 0) {
                    types.remove(type);
                } else {
                    types.put(type, value);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}

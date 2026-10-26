class Solution {
    public boolean containsDuplicate(int[] nums) {
        // avoid resize 
        int capacity = (int) (nums.length / 0.75f) + 1;
        Set<Integer> hashset = new HashSet<>(capacity);

        for (int num : nums) {
            if (!hashset.add(num)) {
                return true;
            }
        }
        return false;
    }
}
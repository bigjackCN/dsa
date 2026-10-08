/*
Two Pointers

When to use:
- The input is SORTED (or can be sorted cheaply), or has a structure
  (palindrome, linked list cycle) where two positions can be reasoned
  about together.
- Keywords: "sorted array", "pair that sums to", "palindrome", "in place",
  "constant extra space", "remove duplicates".

Core idea:
Use two indices and move them according to a rule that lets you DISCARD
elements for good, so the pair of pointers does O(n) total work instead of
checking all O(n^2) pairs. The whole technique depends on being able to
justify each discard ("this element can't be part of any answer because
...").

Variants:

1. Opposite ends (this file's template): left = 0, right = n - 1, move
   toward each other.

       int left = 0, right = n - 1;
       while (left < right) {
           // evaluate arr[left], arr[right]
           // too small  -> left++   (discard arr[left])
           // too large  -> right--  (discard arr[right])
           // match      -> record / return
       }

2. Same direction, fast and slow (read pointer / write pointer): used for
   in-place filtering and linked list problems (see leetcode/141.java,
   leetcode/876.java for fast/slow on linked lists).

3. Two pointers over two different arrays/strings (merge-style).

How it differs from sliding window:
- Sliding window keeps a contiguous range [left, right] and its running
  state; both ends only move forward.
- Opposite-ends two pointers moves from the outside in, and the justification
  for each move comes from sortedness / monotonicity, not from window state.

Problems solved with this pattern:
- LC 167 - Two Sum II, Sorted Input (leetcode/167.java)
  Opposite ends. Discard left when sum is too small, right when too large.
  O(n) time, O(1) space; contrast with LC 1 HashMap solution (O(n) space).
- LC 15 - 3Sum (leetcode/15.java)
  Sort, fix nums[i], then opposite-ends two pointers on the rest. O(n^2).
  Duplicate handling WITHOUT a Set:
    * outer: if (i > 0 && nums[i] == nums[i-1]) continue;  (compare with
      PREVIOUS so the first copy still explores triplets reusing the value)
    * inner, after recording a match: skip equal values on both sides,
      THEN left++ and right-- once more to step past the used triplet.
  Generalizes to kSum: fix k-2 values, two pointers on the rest.
- LC 11 - Container With Most Water (leetcode/11.java)
  Opposite ends, but the move rule is "discard the SHORTER line", justified
  by: any container using the shorter line with something inside is capped
  by that line's height and has less width, so it can't beat what we
  already recorded. Exactly n - 1 iterations, O(1) space.
*/

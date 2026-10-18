/*
Binary Search

When to use:
- The input is sorted, or the answer lies in a range where a yes/no test is
  MONOTONIC (false ... false true ... true).
- Keywords: "sorted", "O(log n)", "minimum/maximum value such that ...",
  "first/last position of ...".

Core idea:
Keep a range that must contain the answer, test the middle, and discard the
half that cannot contain it. Each step halves the range: O(log n).

Template 1 -- find an exact value (inclusive bounds):

    int left = 0, right = n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;   // overflow-safe
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) right = mid - 1;
        else left = mid + 1;
    }
    return -1;

Template 2 -- exclusive upper bound (find first index where a condition
becomes true):

    int left = 0, right = n;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (condition(mid)) right = mid;       // mid might be the answer
        else left = mid + 1;
    }
    return left;

Pairing rule: the loop condition and the updates depend on whether `right`
is INSIDE the range (inclusive: <=, mid - 1) or OUTSIDE it (exclusive:
<, mid). Pick one style and stay with it; mixing them skips elements or
loops forever.

Variant -- rotated sorted array (LC 33): use Template 1, but at each mid one
half is always sorted. Decide which with `nums[mid] < nums[right]` (right half
sorted) else left half sorted. Range-check the target against the sorted half:
inside -> go there, otherwise go to the other half. With duplicates (LC 81),
nums[mid] == nums[right] is ambiguous -> do right-- (worst case O(n)).

Checklist before saying "done":
- Trace [x] (one element), [x, y] (two elements), target smaller than all,
  target larger than all, target absent.
- Does every iteration strictly shrink the range?

Problems solved with this pattern:
- LC 704 - Binary Search (leetcode/704.java)
  Template 1. Bug seen: while (left < right) with inclusive bounds skipped
  the last remaining element.
- LC 33  - Search in Rotated Sorted Array (leetcode/33.java)
  Template 1 + "find the sorted half" step. Key insight: at any mid, one half
  is always ascending, so a range check tells you which half has the target.
*/

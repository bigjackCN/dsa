/*
Sliding Window

When to use:
- Problems about a contiguous substring/subarray that must satisfy some
  condition (no repeats, at most K distinct chars, sum >= target, etc.)
- Keywords to watch for: "substring", "subarray", "contiguous", "longest/
  shortest window that satisfies ..."

Core idea:
Maintain a window [left, right] that always stays valid. Grow it from the
right every iteration; only shrink it from the left when the window becomes
invalid. Because left only ever moves forward, it moves at most n times
total across the whole run, so even with a nested while loop the overall
time is O(n), not O(n^2).

Template:

    int left = 0;
    for (int right = 0; right < n; right++) {
        // 1. add arr[right] into the window

        while (/* window is invalid */) {
            // 2. remove arr[left] from the window
            left++;
        }

        // 3. window [left, right] is now valid -- update the answer
        // e.g. max = Math.max(max, right - left + 1);
    }

Variants seen so far:
- Fixed-size window: skip the while loop, just slide and check once
  right - left + 1 == k.
- "At most K" / "no repeats": shrink with a while loop until valid again
  (this file's template).
- Track window state with a HashSet (presence only), or a HashMap/int[]
  array (counts), depending on whether you need frequency info.

Problems solved with this pattern:
- LC 3  - Longest Substring Without Repeating Characters (leetcode/3.java)
  Window state: HashSet<Character> (presence only, no repeats allowed).
*/

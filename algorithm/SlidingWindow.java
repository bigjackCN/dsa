/*
Sliding Window

When to use:
- Problems about a contiguous substring/subarray that must satisfy some
  condition (no repeats, at most K distinct chars, sum >= target, etc.)
- Keywords to watch for: "substring", "subarray", "contiguous", "longest/
  shortest window that satisfies ..."

Core idea:
Maintain a window [left, right]. Grow it from the right every iteration,
then adjust from the left with a while loop. Because left only ever moves
forward, it moves at most n times total across the whole run, so even with
a nested while loop the overall time is O(n), not O(n^2).

There are two mirror-image templates depending on whether you're looking
for the LONGEST or SHORTEST valid window -- mixing them up is the most
common bug in this pattern.

Template A -- longest valid window (shrink WHILE INVALID, i.e. shrink
until valid again, then record):

    int left = 0;
    for (int right = 0; right < n; right++) {
        // 1. add arr[right] into the window

        while (/* window is invalid */) {
            // 2. remove arr[left] from the window
            left++;
        }

        // 3. window [left, right] is now valid -- record it
        // e.g. max = Math.max(max, right - left + 1);
    }

Template B -- shortest valid window (shrink WHILE STILL VALID, recording
on every single step of the shrink, not just before/after it):

    int left = 0;
    for (int right = 0; right < n; right++) {
        // 1. add arr[right] into the window

        while (/* window is valid */) {
            // 2. record FIRST, before shrinking further
            // e.g. min = Math.min(min, right - left + 1);

            // 3. then remove arr[left] from the window
            left++;
        }
    }

The bug to watch for in Template B: if you only check validity once before
the while loop, or once after it exits, you can skip over a valid window
that existed mid-shrink -- especially with values that can jump by more
than 1 per step (e.g. summing positive integers, where removing one
element can overshoot past the target in a single step). The fix is to
make the check the FIRST thing inside the while loop, so every valid state
gets recorded, not just the boundary states.

Variants seen so far:
- Fixed-size window: skip the while loop, just slide and check once
  right - left + 1 == k.
- "At most K" / "no repeats" (longest window): Template A.
- "Minimal length that satisfies sum/condition" (shortest window): Template B.
- Track window state with a HashSet (presence only), a HashMap/int[] array
  (counts), or a running numeric accumulator (sum), depending on the
  condition being tracked.

Problems solved with this pattern:
- LC 3   - Longest Substring Without Repeating Characters (leetcode/3.java)
  Template A. Window state: HashSet<Character> (presence only).
- LC 209 - Minimum Size Subarray Sum (leetcode/209.java)
  Template B. Window state: running int sum. Classic case of the
  check-mid-shrink bug described above -- values can drop by more than 1
  per removal, so a valid window can exist between shrink steps.
- LC 424 - Longest Repeating Character Replacement (leetcode/424.java)
  Template A. Window state: int[26] frequency array + a monotonic
  (never-decreased) maxFreq upper bound. See follow-up note below.
- LC 567 - Permutation in String (leetcode/567.java)
  Fixed-size window (length = s1.length()). Window state: int[26] counts,
  compared against s1's counts with Arrays.equals (O(26) = O(1)). Pattern:
  pre-fill first m-1 chars, then add right / compare / remove left.
- LC 904 - Fruit Into Baskets (leetcode/904.java)
  Template A. "At most K distinct" window (K = 2). Window state:
  HashMap<type, count>; shrink while map.size() > K and remove the key
  when its count reaches 0. Space is O(K), not O(n).
*/

/*
Follow-up note (after LC 424):

Template A doesn't always need the "window invalid" check to be perfectly
accurate on every shrink step. If the quantity you're tracking (like
maxFreq in LC 424) only ever needs to be a safe upper bound rather than
the exact current value, you can skip recomputing it on shrink entirely.
This trades a small amount of "precision" (you might under-shrink by a
little) for simplicity, and for longest-window problems this is still
correct overall, because the final answer only cares about the best valid
window ever seen, not about the window being minimal at every point in
time. Contrast with Template B (LC 209), where under-tracking the window
state would silently skip over the actual answer -- there the check must
be exact at every step. Longest-window problems can tolerate looseness
in the shrink condition; shortest-window problems generally cannot.
*/

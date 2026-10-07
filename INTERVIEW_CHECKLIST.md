# Interview Checklist

Review this before each practice session. Based on observed habits across
sliding window and two pointers practice (Oct 2026).

## Before coding (first 5 minutes)

- [ ] Ask clarifying questions: empty/null input, value ranges, duplicates,
      sorted or not, 0- vs 1-indexed output, what to return if no answer.
- [ ] Restate the problem in my own words (catches misreads like
      "permutation" vs "subsequence").
- [ ] Walk through one example by hand.
- [ ] State the brute force and its complexity, then the better idea.
- [ ] Say the plan out loud and get a go-ahead before typing.
- [ ] Before borrowing a pattern/formula, ask: what quantity does THIS
      problem actually limit?

## While coding

- [ ] Write a comment skeleton first for loop-heavy problems, then fill it in.
- [ ] No autocomplete in real interviews: type every bracket, semicolon,
      and import by hand.

## Before saying "done" (read as the compiler)

- [ ] `length` (arrays) vs `length()` (String) vs `size()` (collections).
- [ ] Every variable is declared and spelled consistently.
- [ ] `==` for comparison, `=` for assignment; `>=` not `=>`.
- [ ] Imports present (`java.util.*`), semicolons, balanced braces/parens.
- [ ] Return statement on every path; result initialized.
- [ ] Trace the TINIEST input (1-3 elements) to confirm loops terminate.
- [ ] Trace the given examples, then one edge case (empty, all same, no answer).
- [ ] For shrink-while-valid loops: record the answer INSIDE the loop.
- [ ] After recording a match in a two-pointer loop: do both pointers move?

## After coding: explain it

- [ ] Time complexity with the reason (amortized? what bounds each loop?).
- [ ] Space complexity: what bounds the data structure's size?
- [ ] Why is each pointer move/shrink SAFE? ("this element can be ruled
      out because ...")
- [ ] Mention one alternative approach and its trade-off.

## Topic roadmap (target: offer by Dec 2026)

Done:
- [x] Sliding window (LC 3, 209, 424, 567, 904)
- [x] Two pointers basics (LC 167, 15)
- [x] Linked list basics (LC 83, 141, 203, 206, 237, 876)
- [x] Hash map basics (NeetCode: TwoSum, ContainsDuplicate, ValidAnagram)

Next, in rough priority order:
- [ ] Behavioral: "tell me about yourself", STAR stories (before applying)
- [ ] Easy two pointers rep: Valid Palindrome, Container With Most Water
- [ ] Binary search
- [ ] Stack
- [ ] Trees: DFS/BFS
- [ ] Graphs: BFS/DFS, islands
- [ ] Intervals, heaps
- [ ] Basic dynamic programming
- [ ] Java backend: collections internals, concurrency basics, SQL, REST/Spring

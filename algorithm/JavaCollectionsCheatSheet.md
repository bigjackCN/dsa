# Java Collections Cheat Sheet (for coding interviews)

Return values are the part worth memorizing.

## Queue / Deque (use ArrayDeque; PriorityQueue is a min-heap by default)

| Method | Does | If empty / full |
|---|---|---|
| `offer(e)` / `add(e)` | add to tail | `offer` returns false, `add` throws |
| `poll()` / `remove()` | remove head and return it | `poll` returns null, `remove` throws |
| `peek()` / `element()` | look at head | `peek` returns null, `element` throws |
| `push(e)` / `pop()` | stack ops at the HEAD | `push` is void, `pop` throws |

- Also: `addFirst/addLast`, `pollFirst/pollLast`, `peekFirst/peekLast`.
- Stack: `Deque<Integer> stack = new ArrayDeque<>();` (preferred over `Stack`).
- Max-heap: `new PriorityQueue<>(Collections.reverseOrder())`.

## List

- `add(e)` returns boolean (true); `add(i, e)` is void.
- `get(i)`; `set(i, e)` returns the OLD value.
- `remove(int i)` returns the removed element; `remove(Object o)` returns boolean.
- TRAP: on `List<Integer>`, `list.remove(5)` removes INDEX 5.
  Use `list.remove(Integer.valueOf(5))` to remove the value.
- `Arrays.asList(a, b, c)`: fixed-size (can `set`, cannot `add`/`remove`).
- `List.of(...)`: immutable.

## Map

- `put(k, v)` returns the PREVIOUS value, or null.
- `get(k)` returns null if missing; `getOrDefault(k, d)`.
- `containsKey(k)`; `remove(k)` returns the value.
- `putIfAbsent(k, v)` returns the existing value, or null.
- `merge(k, 1, Integer::sum)`: count in one line.
- `computeIfAbsent(k, x -> new ArrayList<>()).add(v)`: group in one line.

## Set

- `add(e)` returns false if it was already present (handy for "seen before").
- `remove(e)` returns boolean; `contains(e)`.

## Arrays / Collections

- `Arrays.sort(a)` is void; `Arrays.fill`; `Arrays.equals` returns boolean.
- `Arrays.toString(a)`; `Arrays.copyOfRange(a, from, toExclusive)`.
- `Collections.sort(list)`; `Collections.reverse(list)`.

## String / StringBuilder

- `s.length()` `s.charAt(i)` `s.substring(begin, endExclusive)` `s.toCharArray()`
- `sb.append(x)` `sb.reverse()` `sb.insert(i, x)` `sb.deleteCharAt(i)` `sb.toString()`
- `Character.isLetterOrDigit(c)` `Character.toLowerCase(c)`

## Length: field or method?

- array: `arr.length` (field, no parentheses)
- String: `s.length()` (method)
- collections: `list.size()` / `map.size()`

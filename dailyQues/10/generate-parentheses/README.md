# Generate Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Backtracking` `Bracket Sequences`  
**Time:** O(4^n / sqrt(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
      func(new StringBuilder(""),0,0,n);
      return ans;   
    }
    public void func(StringBuilder sb, int open, int close, int n){
        if(close == n){
            ans.add(new String(sb.toString()));
            return;
        }
        if(open<n){
            sb.append("(");
            func(sb,open+1,close,n);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close < open){
            sb.append(")");
            func(sb,open,close+1,n);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
```

---

---
## Quick Revision
Given an integer n, generate all combinations of well-formed parentheses.
This problem is solved using backtracking to explore all valid combinations.

## Intuition
The core idea is to build valid parentheses strings incrementally. At any point, we can add an opening parenthesis if we haven't used all `n` available opening parentheses. We can add a closing parenthesis only if the number of closing parentheses is strictly less than the number of opening parentheses already placed. This ensures that we never have an invalid prefix like `)(`. When we have placed `n` closing parentheses, we have a complete and valid combination.

## Algorithm
1. Initialize an empty list `ans` to store the valid parentheses combinations.
2. Define a recursive helper function `func` that takes:
    - `sb`: a `StringBuilder` representing the current string being built.
    - `open`: the count of opening parentheses used so far.
    - `close`: the count of closing parentheses used so far.
    - `n`: the total number of pairs of parentheses required.
3. **Base Case:** If `close` equals `n`, it means we have formed a complete and valid parentheses string. Add the current `sb` to the `ans` list and return.
4. **Recursive Step 1 (Add Open Parenthesis):** If `open` is less than `n`, we can add an opening parenthesis.
    - Append `(` to `sb`.
    - Recursively call `func` with `open + 1`.
    - **Backtrack:** Remove the last appended `(` from `sb` to explore other possibilities.
5. **Recursive Step 2 (Add Close Parenthesis):** If `close` is less than `open`, we can add a closing parenthesis. This condition ensures that we don't add a closing parenthesis without a corresponding open one.
    - Append `)` to `sb`.
    - Recursively call `func` with `close + 1`.
    - **Backtrack:** Remove the last appended `)` from `sb` to explore other possibilities.
6. Start the process by calling `func` with an empty `StringBuilder`, `open = 0`, `close = 0`, and the given `n`.
7. Return the `ans` list.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems that involve exploring a search space by trying to build a solution incrementally, one piece at a time, and abandoning a path (backtracking) as soon as it's determined that the path cannot lead to a valid solution.
*   **Recursion:** The process of defining a problem in terms of itself. This is crucial for exploring the tree of possibilities in backtracking.
*   **State Management:** Carefully managing the state (number of open and close parentheses) is key to ensuring valid combinations are generated.

## Common Mistakes
*   Not handling the base case correctly, leading to infinite recursion or incomplete results.
*   Incorrectly applying the conditions for adding open and close parentheses, resulting in invalid combinations (e.g., `)(` or `())`).
*   Forgetting to backtrack (remove characters from `StringBuilder`) after a recursive call, which corrupts the state for subsequent branches.
*   Not understanding the constraints on `open` and `close` counts, which are essential for well-formedness.

## Complexity Analysis
- Time: O(4^n / sqrt(n)) - This is related to the Catalan numbers. For each `n`, the number of valid parentheses combinations grows exponentially. The `4^n` comes from the fact that at each step, we have at most two choices (add '(' or ')'), and `n` is the depth of recursion. The `sqrt(n)` factor is a tighter bound derived from Catalan number properties.
- Space: O(n) - This is due to the recursion depth, which can go up to `n` levels (for `n` pairs of parentheses), and the `StringBuilder` which also stores up to `2n` characters.

## Commented Code
```java
class Solution {
    // List to store all valid combinations of parentheses.
    List<String> ans = new ArrayList<>();

    // Main function to initiate the generation process.
    public List<String> generateParenthesis(int n) {
      // Start the recursive helper function with an empty string builder,
      // 0 open parentheses, 0 close parentheses, and the target number of pairs 'n'.
      func(new StringBuilder(""), 0, 0, n);
      // Return the list containing all generated valid parentheses combinations.
      return ans;
    }

    // Recursive helper function to build parentheses combinations.
    public void func(StringBuilder sb, int open, int close, int n) {
        // Base case: If the number of closing parentheses equals 'n',
        // we have formed a complete and valid combination.
        if (close == n) {
            // Add the current valid string to the result list.
            ans.add(new String(sb.toString()));
            // Stop this recursive path.
            return;
        }

        // Recursive step 1: If we can add an opening parenthesis (i.e., we haven't used all 'n' open ones).
        if (open < n) {
            // Append an opening parenthesis to the current string builder.
            sb.append("(");
            // Recursively call func, incrementing the count of open parentheses.
            func(sb, open + 1, close, n);
            // Backtrack: Remove the last appended opening parenthesis to explore other possibilities.
            sb.deleteCharAt(sb.length() - 1);
        }

        // Recursive step 2: If we can add a closing parenthesis (i.e., the number of close parentheses
        // is less than the number of open parentheses, ensuring well-formedness).
        if (close < open) {
            // Append a closing parenthesis to the current string builder.
            sb.append(")");
            // Recursively call func, incrementing the count of close parentheses.
            func(sb, open, close + 1, n);
            // Backtrack: Remove the last appended closing parenthesis to explore other possibilities.
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach and the conditions for adding parentheses.
*   Walk through an example (e.g., n=2) step-by-step to demonstrate your understanding of the recursion and backtracking.
*   Discuss the time and space complexity, and how it relates to Catalan numbers if you're familiar with them.
*   Be prepared to discuss edge cases, like n=0 or n=1.

## Revision Checklist
- [ ] Understand the problem statement for generating well-formed parentheses.
- [ ] Grasp the backtracking approach and its core logic.
- [ ] Implement the recursive helper function with correct base cases and recursive steps.
- [ ] Ensure proper state management for `open` and `close` counts.
- [ ] Implement backtracking (removing characters) correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly and concisely.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Recursion` `String`

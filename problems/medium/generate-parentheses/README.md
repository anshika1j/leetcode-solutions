# Generate Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Backtracking` `Bracket Sequences`  
**Time:** O(2^n / n^(3/2)  
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
The problem asks to generate all possible combinations of well-formed parentheses for a given number of pairs. This can be solved by using a recursive function to build up the combinations.

## Intuition
The key insight is that each combination of well-formed parentheses can be thought of as a series of opening and closing parentheses. We can use a recursive function to build up the combinations by adding opening parentheses when there are available pairs and adding closing parentheses when necessary.

## Algorithm
1. Initialize an empty list to store the combinations.
2. Define a recursive function `func` that takes a string builder, the current number of opening and closing parentheses, and the total number of pairs.
3. If the number of closing parentheses is equal to the total number of pairs, add the current combination to the list.
4. If the number of opening parentheses is less than the total number of pairs, add an opening parenthesis to the combination and recursively call `func` with the updated parameters.
5. If the number of closing parentheses is less than the number of opening parentheses, add a closing parenthesis to the combination and recursively call `func` with the updated parameters.
6. Return the list of combinations.

## Concept to Remember
* Recursion is a powerful tool for solving problems that have a recursive structure.
* Using a string builder can be more efficient than concatenating strings in a loop.
* The time and space complexity of a recursive function can be analyzed by considering the maximum depth of the recursion tree.

## Common Mistakes
* Failing to properly handle the base case of the recursion.
* Adding unnecessary parameters to the recursive function.
* Failing to remove the closing parenthesis when it's no longer needed.

## Complexity Analysis
- Time: O(2^n / n^(3/2)) - The time complexity is exponential in the number of pairs, but can be reduced by using a more efficient algorithm.
- Space: O(n) - The space complexity is linear in the number of pairs, as we need to store the combinations.

## Commented Code
```java
class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        func(new StringBuilder(""), 0, 0, n);
        return ans;
    }

    public void func(StringBuilder sb, int open, int close, int n) {
        // Base case: if the number of closing parentheses is equal to the total number of pairs, add the combination to the list.
        if (close == n) {
            ans.add(new String(sb.toString()));
            return;
        }

        // Add an opening parenthesis when there are available pairs.
        if (open < n) {
            sb.append("(");
            func(sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        // Add a closing parenthesis when necessary.
        if (close < open) {
            sb.append(")");
            func(sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

## Interview Tips
* Make sure to properly handle the base case of the recursion.
* Use a string builder to build up the combinations efficiently.
* Consider using a more efficient algorithm to reduce the time complexity.
* Practice solving problems with recursion to improve your skills.

## Revision Checklist
- [ ] Review the time and space complexity of the algorithm.
- [ ] Practice solving problems with recursion.
- [ ] Consider using a more efficient algorithm to reduce the time complexity.

## Similar Problems
* Generate Parentheses II (Medium)
* Generate Parentheses III (Hard)

## Tags
`Array` `Hash Map` `String` `Recursion`

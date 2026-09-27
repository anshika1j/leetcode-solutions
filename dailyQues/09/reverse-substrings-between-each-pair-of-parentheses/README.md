# Reverse Substrings Between Each Pair Of Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Bracket Sequences`  
**Time:** O(N^2)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String reverseParentheses(String s) {
        // on seeing close bracket -> pop() reverse() append() till open bracket
        Stack<String> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(c==')'){
                StringBuilder sb = new StringBuilder();
                while(!st.peek().equals("(")){
                    String curr = st.pop();
                    if(curr.length()!=1) curr = new StringBuilder(curr).reverse().toString();
                    sb.append(curr);
                }
                st.pop(); 
                st.push(sb.toString());
            } else st.push(Character.toString(c));
        }
        // System.out.println(st);
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) sb.insert(0,st.pop());
        return sb.toString();
    }
}

```

---

---
## Quick Revision
Reverse substrings within nested parentheses.
Use a stack to manage segments and reverse them upon encountering closing parentheses.

## Intuition
The core idea is that when we encounter a closing parenthesis ')', we need to reverse everything that was encountered since the matching opening parenthesis '('. A stack is a natural data structure for this because it follows a Last-In, First-Out (LIFO) principle, allowing us to easily retrieve the most recently opened segment. We can build up segments of characters and then reverse them when a ')' signals the end of a segment to be processed.

## Algorithm
1. Initialize an empty stack of strings (`st`).
2. Iterate through each character (`c`) of the input string `s`.
3. If `c` is a closing parenthesis ')':
    a. Initialize an empty `StringBuilder` (`sb`).
    b. While the top element of the stack (`st.peek()`) is not an opening parenthesis '(':
        i. Pop the top string from the stack.
        ii. If the popped string has a length greater than 1, reverse it.
        iii. Append the (potentially reversed) popped string to `sb`.
    c. Pop the opening parenthesis '(' from the stack.
    d. Push the content of `sb` (which now holds the reversed segment) back onto the stack.
4. If `c` is not a closing parenthesis, push its string representation onto the stack.
5. After iterating through all characters, initialize another `StringBuilder` (`sb`).
6. While the stack is not empty, pop elements from the stack and insert them at the beginning of `sb`. This reconstructs the final string in the correct order.
7. Return the string representation of `sb`.

## Concept to Remember
*   **Stack Data Structure:** Essential for managing nested structures and LIFO operations.
*   **String Manipulation:** Efficiently building and reversing strings using `StringBuilder`.
*   **Recursion/Nesting:** Understanding how to handle nested structures, which is implicitly managed by the stack here.

## Common Mistakes
*   **Incorrect Reversal Logic:** Forgetting to reverse segments that are longer than a single character.
*   **Stack Underflow/Overflow:** Mishandling the popping of '(' or pushing incorrect elements.
*   **Order of Reconstruction:** Not correctly reassembling the final string from the stack elements, leading to an incorrect order.
*   **Inefficient String Concatenation:** Using `+` operator repeatedly instead of `StringBuilder` for string building.

## Complexity Analysis
*   **Time:** O(N^2) - In the worst case, each character might be pushed and popped multiple times, and string reversals can take O(length of segment). For example, `((((a))))` would involve multiple reversals of growing substrings.
*   **Space:** O(N) - The stack can store up to N characters in the worst case (e.g., a string with no parentheses or deeply nested ones).

## Commented Code
```java
class Solution {
    public String reverseParentheses(String s) {
        // Initialize a stack to store string segments.
        Stack<String> st = new Stack<>();
        // Iterate through each character of the input string.
        for(char c : s.toCharArray()){
            // If the character is a closing parenthesis ')'.
            if(c==')'){
                // Initialize a StringBuilder to build the reversed segment.
                StringBuilder sb = new StringBuilder();
                // While the top of the stack is not an opening parenthesis '('.
                while(!st.peek().equals("(")){
                    // Pop the current segment from the stack.
                    String curr = st.pop();
                    // If the popped segment is longer than a single character, reverse it.
                    if(curr.length()!=1) curr = new StringBuilder(curr).reverse().toString();
                    // Append the (potentially reversed) segment to our builder.
                    sb.append(curr);
                }
                // Pop the opening parenthesis '(' from the stack.
                st.pop(); 
                // Push the fully reversed segment back onto the stack.
                st.push(sb.toString());
            } else {
                // If it's not a closing parenthesis, push its string representation onto the stack.
                st.push(Character.toString(c));
            }
        }
        // Initialize a StringBuilder to construct the final result.
        StringBuilder sb = new StringBuilder();
        // While the stack is not empty.
        while(!st.isEmpty()) {
            // Pop elements from the stack and insert them at the beginning of the result builder.
            // This ensures the correct order as elements were pushed in reverse order of processing.
            sb.insert(0,st.pop());
        }
        // Return the final reconstructed string.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Explain the Stack's Role:** Clearly articulate why a stack is suitable for handling nested structures and the LIFO requirement for reversing.
*   **Trace with an Example:** Walk through a simple example like `(abcd)` or `(u(love)i)` to demonstrate your understanding of the algorithm's steps.
*   **Discuss Edge Cases:** Consider cases like empty strings, strings with no parentheses, or strings with only one type of parenthesis.
*   **Clarify Reversal:** Ensure you explain *when* and *how* the reversal happens (i.e., only when a ')' is encountered and for the segment between matching parentheses).

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the need for a stack to handle nesting.
- [ ] Implement the logic for processing ')' and reversing segments.
- [ ] Ensure correct handling of '(' and pushing characters.
- [ ] Verify the final string reconstruction from the stack.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 20: Valid Parentheses
*   LeetCode 301: Remove Invalid Parentheses
*   LeetCode 71: Simplify Path

## Tags
`Stack` `String` `StringBuilder`

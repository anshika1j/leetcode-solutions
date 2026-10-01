# Valid Parentheses

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String` `Stack` `Bracket Sequences`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public boolean isValid(String s) {
       Stack<Character> st = new Stack<>();
        for(char c: s.toCharArray()){
            if(c=='(') st.push(')');
            else if(c=='{') st.push('}');
            else if(c=='[') st.push(']');
            else{
                if(st.isEmpty() || c!=st.pop()) return false;
            }
        }        
        return st.isEmpty();
    }
}
```

---

---
## Quick Revision
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
A string is valid if open brackets are closed by the same type of brackets and in the correct order.

## Intuition
The core idea is that when we encounter an opening bracket, we expect a corresponding closing bracket later. The crucial part is that the *most recently opened* bracket must be the *first one to be closed*. This Last-In, First-Out (LIFO) behavior strongly suggests using a stack. When we see an opening bracket, we push its *expected closing bracket* onto the stack. When we see a closing bracket, we check if it matches the top of the stack (which represents the expected closing bracket for the most recent open one).

## Algorithm
1. Initialize an empty stack.
2. Iterate through each character in the input string `s`.
3. If the character is an opening bracket ('(', '{', or '['):
    a. Push its corresponding closing bracket (')', '}', or ']') onto the stack.
4. If the character is a closing bracket (')', '}', or ']'):
    a. Check if the stack is empty. If it is, it means we have a closing bracket without a corresponding opening bracket, so return `false`.
    b. Pop the top element from the stack.
    c. Compare the popped element with the current character. If they do not match, it means the brackets are not closed in the correct order, so return `false`.
5. After iterating through all characters, check if the stack is empty. If it is, all opening brackets have been correctly closed, so return `true`. If the stack is not empty, it means there are unclosed opening brackets, so return `false`.

## Concept to Remember
*   **Stacks (LIFO):** Essential for problems where the order of operations or matching requires processing the most recent item first.
*   **Bracket Matching:** Understanding how to pair opening and closing delimiters.
*   **State Management:** The stack effectively keeps track of the "state" of open brackets that are awaiting closure.

## Common Mistakes
*   Forgetting to handle the case where a closing bracket appears before any opening bracket (stack is empty).
*   Not checking if the stack is empty *before* popping when a closing bracket is encountered.
*   Pushing the opening bracket onto the stack instead of its corresponding closing bracket.
*   Failing to check if the stack is empty at the end to ensure all brackets were closed.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the input string once, and stack operations (push, pop, isEmpty) take constant time.
- Space: O(n) - reason: In the worst case (e.g., a string of all opening brackets like "((((("), the stack can store up to n characters.

## Commented Code
```java
class Solution {
    public boolean isValid(String s) {
       // Initialize a stack to store the expected closing brackets.
       Stack<Character> st = new Stack<>();
        // Iterate over each character in the input string.
        for(char c: s.toCharArray()){
            // If the character is an opening parenthesis, push its corresponding closing parenthesis onto the stack.
            if(c=='(') st.push(')');
            // If the character is an opening curly brace, push its corresponding closing curly brace onto the stack.
            else if(c=='{') st.push('}');
            // If the character is an opening square bracket, push its corresponding closing square bracket onto the stack.
            else if(c=='[') st.push(']');
            // If the character is a closing bracket.
            else{
                // Check if the stack is empty OR if the current closing bracket does not match the top of the stack.
                // If either condition is true, the string is invalid.
                if(st.isEmpty() || c!=st.pop()) return false;
            }
        }        
        // After iterating through the string, if the stack is empty, all brackets were matched correctly.
        // Otherwise, there are unmatched opening brackets.
        return st.isEmpty();
    }
}
```

## Interview Tips
*   Clearly explain the LIFO nature of the problem and why a stack is the appropriate data structure.
*   Walk through an example like `"{[()]}"` step-by-step, showing how the stack changes.
*   Discuss edge cases: empty string, string with only opening brackets, string with only closing brackets, mismatched brackets.
*   Be prepared to explain the time and space complexity.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the LIFO pattern.
- [ ] Implement the stack-based solution.
- [ ] Handle all types of brackets.
- [ ] Correctly check for empty stack before popping.
- [ ] Verify the stack is empty at the end.
- [ ] Analyze time and space complexity.

## Similar Problems
Valid Parentheses II (LeetCode 20)
Longest Valid Parentheses (LeetCode 32)
Remove Invalid Parentheses (LeetCode 301)

## Tags
`Stack` `String` `Array` `Hash Map`

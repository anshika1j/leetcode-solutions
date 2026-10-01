# Expression Add Operators

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Math` `String` `Backtracking`  
**Time:** O(4^n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    List<String> ans = new ArrayList<>();
    String num;
    int target;
    public List<String> addOperators(String n, int t) {
       num = n;
       target = t;
       func(new StringBuilder(""),0,0, 0);
       return ans;
    }
    private void func(StringBuilder curr, long res, long prev, int i){
        if(i==num.length()){
            System.out.println(curr);
            System.out.println(res);
            System.out.println();
            if(res == target) ans.add(new String(curr.toString()));
            return;
        }
        int ogLength = curr.length();
        long value = 0;
        for(int j=i;j<num.length();j++){
            value  = value*10 + cint(num,j);
            
            if(j>i && num.charAt(j) == '0') break;
            
            if(i==0){
                curr.append(value);
                func(curr, value, value, j+1);
                curr.setLength(ogLength);
                continue;
            }
            // multipluy
            curr.append("*");
            curr.append(value);
            func(curr, res -prev + (prev*value), prev*value, j+1);
            curr.setLength(ogLength);
            
            // ayed
            curr.append("+");
            curr.append(value);
            func(curr, (res + value), value, j+1);
            curr.setLength(ogLength);
            
            // minoos
            curr.append("-");
            curr.append(value);
            func(curr, (res - value), -value, j+1);
            curr.setLength(ogLength);
        }
      
    }
    private int cint(String num, int i){
        return Character.getNumericValue(num.charAt(i));
    }
}
```

---

---
## Quick Revision
The problem is to find all possible expressions that evaluate to a given target value using the digits of a given number. We solve this by using a recursive function that tries different operations at each step.

## Intuition
The key insight is that we need to consider not only the current digit, but also the previous digits and the operations that can be applied to them. This is because the operation that is applied to the previous digits can affect the possible operations that can be applied to the current digit.

## Algorithm
1. Define a recursive function `func` that takes a `StringBuilder` to build the expression, the current result, the previous value, and the current index.
2. If the current index is equal to the length of the input string, check if the result is equal to the target, and if so, add the expression to the result list.
3. Otherwise, try adding a digit to the current result and recursively call `func` with the updated result and index.
4. If the current digit is not zero, try multiplying the current result by the current digit and recursively call `func` with the updated result and index.
5. Try adding and subtracting the current digit from the current result and recursively call `func` with the updated result and index.

## Concept to Remember
* Recursive function design
* Consideration of previous values and operations
* Handling of different operations (addition, subtraction, multiplication)

## Common Mistakes
* Not considering the case where the current digit is zero
* Not handling the case where the result is negative
* Not checking for overflow when multiplying large numbers

## Complexity Analysis
- Time: O(4^n) where n is the length of the input string
- Space: O(n) for the recursive call stack

## Commented Code
```java
class Solution {
    List<String> ans = new ArrayList<>();
    String num;
    int target;
    public List<String> addOperators(String n, int t) {
        num = n;
        target = t;
        func(new StringBuilder(""), 0, 0, 0);
        return ans;
    }
    private void func(StringBuilder curr, long res, long prev, int i){
        // Check if the current index is equal to the length of the input string
        if(i==num.length()){
            // Check if the result is equal to the target
            if(res == target) ans.add(new String(curr.toString()));
            return;
        }
        int ogLength = curr.length();
        long value = 0;
        for(int j=i;j<num.length();j++){
            // Get the value of the current digit
            value  = value*10 + cint(num,j);
            
            // Check if the current digit is zero
            if(j>i && num.charAt(j) == '0') break;
            
            // If the current index is zero, add the value to the current result
            if(i==0){
                curr.append(value);
                // Recursively call func with the updated result and index
                func(curr, value, value, j+1);
                curr.setLength(ogLength);
                continue;
            }
            // Try multiplying the current result by the current digit
            curr.append("*");
            curr.append(value);
            // Recursively call func with the updated result and index
            func(curr, res -prev + (prev*value), prev*value, j+1);
            curr.setLength(ogLength);
            
            // Try adding the current digit to the current result
            curr.append("+");
            curr.append(value);
            // Recursively call func with the updated result and index
            func(curr, (res + value), value, j+1);
            curr.setLength(ogLength);
            
            // Try subtracting the current digit from the current result
            curr.append("-");
            curr.append(value);
            // Recursively call func with the updated result and index
            func(curr, (res - value), -value, j+1);
            curr.setLength(ogLength);
        }
      
    }
    private int cint(String num, int i){
        // Get the numeric value of the current character
        return Character.getNumericValue(num.charAt(i));
    }
}
```

## Interview Tips
* Make sure to consider all possible operations at each step
* Use a recursive function to handle the problem in a efficient way
* Pay attention to edge cases, such as when the current digit is zero

## Revision Checklist
- [ ] Consider all possible operations at each step
- [ ] Use a recursive function to handle the problem in an efficient way
- [ ] Pay attention to edge cases

## Similar Problems
* Add Digits
* Longest Common Subsequence
* Edit Distance

## Tags
`Array` `Hash Map` `Recursion` `String`

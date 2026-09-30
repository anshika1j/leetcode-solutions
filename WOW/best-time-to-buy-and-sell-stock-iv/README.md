# Best Time To Buy And Sell Stock Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n*k)  
**Space:** O(n*k)

---

## Solution (java)

```java
class Solution {
   
    int[] arr;
    int[][][] memo;

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        arr = prices;
        memo = new int[n][2][k+1];
        for(int[][] _2d : memo) for(int[] _1d : _2d) Arrays.fill(_1d,-1);
        return func(0,0,k);
    }
   
    public int func(int i, int haveStock, int k){
        if(k==0 || i==arr.length) return 0;
        
        if(memo[i][haveStock][k] != -1) return memo[i][haveStock][k];
        
        //skip
        int ans = func(i+1,haveStock,k);
        
        //sell
        if(haveStock==1) ans= Math.max(ans, func(i+1,0,k-1) + arr[i]); // buying price parent deduct krlega, abhi apna sell add krdo bs
        //buy
        else ans = Math.max(ans, func(i+1,1,k) - arr[i]); // jo bhi profit aayega usme se buying ke paise kaatlena
        
        return memo[i][haveStock][k] = ans;
    }
}

```

---

---
## Quick Revision
Solve the "Best Time to Buy and Sell Stock IV" problem, which involves finding the maximum possible profit from buying and selling a stock with a maximum of k transactions allowed. This problem can be solved using dynamic programming with memoization.

## Intuition
The key insight is to recognize that this problem is essentially a variation of the "Best Time to Buy and Sell Stock" problem, but with the added constraint of a maximum number of transactions (k). By using memoization, we can avoid recalculating the same subproblems multiple times, which would otherwise lead to a time complexity of O(n*k), where n is the number of days.

## Algorithm
1. Initialize a 3D array `memo` to store the maximum profit for each subproblem, with dimensions `n x 2 x (k+1)`.
2. Fill the `memo` array with -1, indicating that the subproblems have not been solved yet.
3. Define a helper function `func` that takes three parameters: the current day `i`, a boolean `haveStock` indicating whether we have a stock or not, and an integer `k` representing the number of transactions remaining.
4. If `k` is 0 or `i` is equal to the last day, return 0, as there are no more transactions to make.
5. If the subproblem has already been solved, return the stored value from `memo`.
6. Recursively call `func` with the next day and the same `haveStock` value (skipping the current day).
7. If we have a stock (`haveStock==1`), recursively call `func` with the next day and `k-1` transactions, and add the current price to the result.
8. If we don't have a stock (`haveStock==0`), recursively call `func` with the next day and `k` transactions, and subtract the current price from the result.
9. Store the maximum profit in `memo` and return the result.

## Concept to Remember
* Dynamic programming with memoization
* Recursion with memoization to avoid recalculating subproblems
* Using a 3D array to store subproblem solutions

## Common Mistakes
* Failing to initialize the `memo` array correctly
* Not properly handling the base cases in the recursive function
* Not storing the maximum profit in the `memo` array correctly

## Complexity Analysis
- Time: O(n*k) - The recursive function is called n times, and each call has a time complexity of O(k) due to the memoization.
- Space: O(n*k) - The `memo` array has dimensions n x 2 x (k+1), which requires O(n*k) space.

## Commented Code
```java
class Solution {
   // 3D array to store subproblem solutions
   int[] arr;
   int[][][] memo;

   public int maxProfit(int k, int[] prices) {
      int n = prices.length;
      arr = prices;
      memo = new int[n][2][k+1];
      // Fill memo array with -1
      for(int[][] _2d : memo) for(int[] _1d : _2d) Arrays.fill(_1d,-1);
      return func(0,0,k);
   }

   public int func(int i, int haveStock, int k){
      // Base cases
      if(k==0 || i==arr.length) return 0;

      // If subproblem has already been solved, return stored value
      if(memo[i][haveStock][k] != -1) return memo[i][haveStock][k];

      // Skip current day
      int ans = func(i+1,haveStock,k);

      // Sell current stock
      if(haveStock==1) {
         ans = Math.max(ans, func(i+1,0,k-1) + arr[i]);
      }
      // Buy current stock
      else {
         ans = Math.max(ans, func(i+1,1,k) - arr[i]);
      }
      // Store maximum profit in memo
      return memo[i][haveStock][k] = ans;
   }
}
```

## Interview Tips
* Make sure to initialize the `memo` array correctly.
* Use memoization to avoid recalculating subproblems.
* Properly handle the base cases in the recursive function.
* Use a clear and consistent naming convention for variables.
* Test the function with different inputs to ensure correctness.

## Revision Checklist
- [ ] Initialize `memo` array correctly
- [ ] Use memoization to avoid recalculating subproblems
- [ ] Properly handle base cases in recursive function
- [ ] Test function with different inputs

## Similar Problems
* "Best Time to Buy and Sell Stock"
* "Best Time to Buy and Sell Stock II"
* "Best Time to Buy and Sell Stock with a Transaction Fee"

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization` `Recursion`

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
Solve a problem where we need to find the maximum profit from buying and selling a stock at most `k` times. We can use a dynamic programming approach with memoization.

## Intuition
The key insight is to use two states: `haveStock` (1 if we have the stock, 0 if we don't) and the remaining number of transactions `k`. We can recursively explore all possible paths and keep track of the maximum profit.

## Algorithm

1. Initialize a 3D memoization table `memo` with size `n x 2 x (k+1)`, where `n` is the number of days.
2. Iterate through the days `i` and consider two possibilities: `skip` or `transaction`.
3. If we skip the current day, recursively call `func(i+1, haveStock, k)` and store the result in `memo`.
4. If we perform a transaction, we have two sub-cases:
	* If we have the stock, we can sell it and get the current price. Recursively call `func(i+1, 0, k-1)` and add the current price to the result.
	* If we don't have the stock, we can buy it and get the negative current price. Recursively call `func(i+1, 1, k)` and subtract the current price from the result.
5. Return the maximum profit from the two possibilities and store it in `memo`.

## Concept to Remember
* **Memoization**: a technique to store the results of expensive function calls to avoid redundant computation.
* **Dynamic Programming**: a problem-solving approach that breaks down a problem into smaller sub-problems and solves each sub-problem only once.
* **Recursion**: a programming technique where a function calls itself to solve a problem.

## Common Mistakes

* Not initializing the memoization table properly, leading to incorrect results.
* Not considering the `k` transactions limit when performing transactions.
* Not using memoization to avoid redundant computation.

## Complexity Analysis
- Time: O(n*k) / We need to iterate through all days and consider all possible transactions.
- Space: O(n*k) / We need to store the memoization table.

## Commented Code
```java
class Solution {
    int[] arr;
    int[][][] memo;

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        arr = prices;
        memo = new int[n][2][k+1];
        // Initialize memoization table
        for(int[][] _2d : memo) for(int[] _1d : _2d) Arrays.fill(_1d,-1);
        return func(0, 0, k);
    }

    public int func(int i, int haveStock, int k){
        if(k==0 || i==arr.length) return 0;
        
        if(memo[i][haveStock][k] != -1) return memo[i][haveStock][k];
        
        // Skip current day
        int ans = func(i+1, haveStock, k);
        
        // Perform transaction
        if(haveStock==1) {
            // Sell stock
            ans = Math.max(ans, func(i+1, 0, k-1) + arr[i]);
        } else {
            // Buy stock
            ans = Math.max(ans, func(i+1, 1, k) - arr[i]);
        }
        
        return memo[i][haveStock][k] = ans;
    }
}
```

## Interview Tips

* Be prepared to explain the memoization and dynamic programming approaches.
* Show that you can handle edge cases, such as `k=0` or `k=n`.
* Practice solving similar problems to improve your skills.

## Revision Checklist
- [ ] Review memoization and dynamic programming concepts.
- [ ] Understand the problem and its constraints.
- [ ] Practice solving similar problems.

## Similar Problems
- Best Time to Buy and Sell Stock III
- Best Time to Buy and Sell Stock II
- Best Time to Buy and Sell Stock

## Tags
`Array` `Hash Map` `Memoization` `Dynamic Programming` `Recursion`

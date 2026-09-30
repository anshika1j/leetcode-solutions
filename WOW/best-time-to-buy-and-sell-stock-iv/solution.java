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

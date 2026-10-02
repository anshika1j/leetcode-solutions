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
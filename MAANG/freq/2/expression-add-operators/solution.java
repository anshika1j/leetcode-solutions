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
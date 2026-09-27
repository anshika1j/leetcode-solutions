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

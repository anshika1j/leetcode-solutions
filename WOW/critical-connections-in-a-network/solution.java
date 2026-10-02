class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    List<List<Integer>> bridges = new ArrayList<>();
    int[] found;
    int[] least;
    int level = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> con) {
        found = new int[n]; // level at which i is found
        least = new int[n]; // the least level i can interact with // degree
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());
        for(List<Integer> pair : con){
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }
        Arrays.fill(found,-1);
        dfs(0,-1);
        return bridges;
    }
    public void dfs(int node, int parent){
        found[node] = least[node] = level++;
        // found = [0,1,3,2]
        // least = [0,0,0,2]
        
        // 0
        for(int neigh : graph.get(node)){
            if(parent == neigh) continue;
            // node=2 neigh=1
            if(found[neigh] == -1){
                dfs(neigh,node);
                //return ke baad ye niche ke check legenge
                least[node] = Math.min(least[node], least[neigh]);
                if(found[node] < least[neigh]) bridges.add(Arrays.asList(node,neigh)); //humaare node ka least humse kam hai, mtlb yahi edge hai wo bridge.
            } else {
                least[node] = Math.min(least[node], found[neigh]); // 3,0
            }
        }
    }
}
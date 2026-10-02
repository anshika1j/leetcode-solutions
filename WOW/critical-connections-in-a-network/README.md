# Critical Connections In A Network

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Depth-First Search` `Graph Theory` `Biconnected Component` `Bridge (Graph)`  
**Time:** O(V + E)  
**Space:** O(V + E)

---

## Solution (java)

```java
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
```

---

---
## Quick Revision
This problem asks to find all "critical connections" (bridges) in a network represented by an undirected graph. Bridges are edges whose removal increases the number of connected components. We solve this using Tarjan's bridge-finding algorithm, which leverages Depth First Search (DFS).

## Intuition
The core idea is to detect if an edge `(u, v)` is a bridge. An edge `(u, v)` is a bridge if there's no "back edge" from `v` or any of its descendants in the DFS tree that leads to `u` or any of its ancestors. If such a back edge exists, it means there's an alternative path between `u` and `v` (or their respective subtrees), so removing `(u, v)` wouldn't disconnect the graph.

We can track this using two arrays: `found` (discovery time or level of a node during DFS) and `least` (the earliest discovery time reachable from a node, including through back edges). If for an edge `(u, v)` (where `v` is a child of `u` in the DFS tree), `least[v]` is greater than `found[u]`, it means `v` and its subtree cannot reach `u` or any ancestor of `u` without using the edge `(u, v)`. Thus, `(u, v)` is a bridge.

## Algorithm
1.  **Graph Representation**: Create an adjacency list to represent the graph from the given connections.
2.  **Initialization**:
    *   Initialize `found` and `least` arrays of size `n` (number of nodes) with -1 (or any indicator that the node hasn't been visited).
    *   Initialize `level` to 0. This will track the discovery time.
    *   Initialize an empty list `bridges` to store the critical connections.
3.  **DFS Traversal**:
    *   Start a DFS from an arbitrary node (e.g., node 0) with `parent` as -1.
    *   Inside the DFS function `dfs(node, parent)`:
        *   Mark the current `node` as visited by setting `found[node] = least[node] = level++`.
        *   Iterate through all neighbors `neigh` of the current `node`.
        *   **Skip Parent**: If `neigh` is the `parent` of the current `node`, skip it to avoid going back up the DFS tree immediately.
        *   **Unvisited Neighbor**: If `neigh` has not been visited (`found[neigh] == -1`):
            *   Recursively call `dfs(neigh, node)`.
            *   **Update `least[node]`**: After the recursive call returns, update `least[node] = Math.min(least[node], least[neigh])`. This propagates the earliest reachable discovery time from the subtree rooted at `neigh` up to `node`.
            *   **Bridge Check**: If `found[node] < least[neigh]`, it means the earliest discovery time reachable from `neigh` (and its subtree) is *after* `node` was discovered. This implies there's no back edge from `neigh`'s subtree to `node` or its ancestors. Therefore, the edge `(node, neigh)` is a bridge. Add `(node, neigh)` to the `bridges` list.
        *   **Visited Neighbor (Back Edge)**: If `neigh` has already been visited (`found[neigh] != -1`) and it's not the parent:
            *   This indicates a back edge. Update `least[node] = Math.min(least[node], found[neigh])`. We use `found[neigh]` because `neigh` is an ancestor (or already visited node in the same component), and we want to know the earliest discovery time we can reach.
4.  **Return Bridges**: After the DFS completes, return the `bridges` list.

## Concept to Remember
*   **Depth First Search (DFS)**: Essential for traversing graph structures and building the DFS tree.
*   **Graph Connectivity**: Understanding how edges contribute to or maintain connectivity.
*   **Back Edges**: Recognizing how back edges in a DFS tree indicate cycles and alternative paths.
*   **Tarjan's Bridge-Finding Algorithm**: A specific application of DFS to find bridges efficiently.

## Common Mistakes
*   **Incorrectly handling the parent node**: Forgetting to `continue` when `neigh == parent` can lead to treating the edge back to the parent as a back edge, corrupting `least` values.
*   **Confusing `found[neigh]` and `least[neigh]` in the back-edge case**: When a neighbor is already visited (and not the parent), we should update `least[node]` with `found[neigh]` (the discovery time of the ancestor), not `least[neigh]`.
*   **Off-by-one errors in `level` or array indexing**: Ensuring `level` is incremented correctly and arrays are accessed within bounds.
*   **Not initializing `found` and `least` arrays properly**: Using default values that don't clearly indicate unvisited nodes.
*   **Misinterpreting the bridge condition `found[node] < least[neigh]`**: This condition is crucial and must be understood correctly.

## Complexity Analysis
- Time: O(V + E) - reason The algorithm performs a single DFS traversal of the graph. Each vertex (V) and each edge (E) is visited at most a constant number of times.
- Space: O(V + E) - reason The space is dominated by the adjacency list representation of the graph (O(V + E)), and the `found` and `least` arrays (O(V)), and the recursion stack for DFS (O(V) in the worst case for a skewed graph).

## Commented Code
```java
class Solution {
    // Adjacency list to represent the graph. Each index corresponds to a node,
    // and the list at that index contains its neighbors.
    List<List<Integer>> graph = new ArrayList<>();
    // List to store the identified critical connections (bridges).
    List<List<Integer>> bridges = new ArrayList<>();
    // Array to store the discovery time (level) at which each node is first visited during DFS.
    // Initialized to -1 to indicate unvisited nodes.
    int[] found;
    // Array to store the lowest discovery time reachable from a node (including through back edges).
    // This is also initialized to -1.
    int[] least;
    // A global counter to assign discovery times (levels) to nodes during DFS.
    int level = 0;

    // Main function to find critical connections.
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> con) {
        // Initialize the found and least arrays with size n.
        found = new int[n];
        least = new int[n];
        // Initialize the adjacency list for n nodes.
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Build the graph by adding edges from the input connections.
        // Since it's an undirected graph, add edges in both directions.
        for(List<Integer> pair : con) {
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }
        // Fill the found array with -1, marking all nodes as unvisited initially.
        Arrays.fill(found, -1);
        // Start the Depth First Search (DFS) from node 0.
        // The parent of the starting node is -1 (or any value that won't be a valid node index).
        dfs(0, -1);
        // Return the list of identified bridges.
        return bridges;
    }

    // Recursive DFS function to traverse the graph and find bridges.
    // 'node' is the current node being visited.
    // 'parent' is the node from which we arrived at the current 'node'.
    public void dfs(int node, int parent) {
        // Mark the current node as visited and set its discovery time and initial least reachable time.
        // 'level' is incremented for each new node visited.
        found[node] = least[node] = level++;

        // Iterate through all neighbors of the current node.
        for(int neigh : graph.get(node)) {
            // If the neighbor is the parent node, skip it to avoid going back up the DFS tree immediately.
            if(parent == neigh) {
                continue;
            }

            // If the neighbor has not been visited yet (found[neigh] is -1).
            if(found[neigh] == -1) {
                // Recursively call DFS on the unvisited neighbor.
                // The current 'node' becomes the 'parent' for the recursive call.
                dfs(neigh, node);

                // After the recursive call returns, update the 'least' reachable time for the current 'node'.
                // It's the minimum of its current 'least' value and the 'least' value of its child 'neigh'.
                // This propagates the earliest reachable time from the subtree up to the current node.
                least[node] = Math.min(least[node], least[neigh]);

                // Check if the edge (node, neigh) is a bridge.
                // A bridge exists if the discovery time of the current node ('found[node]')
                // is less than the earliest reachable time from its child 'neigh' ('least[neigh]').
                // This means there's no back edge from 'neigh' or its subtree to 'node' or any of its ancestors.
                if(found[node] < least[neigh]) {
                    // If it's a bridge, add it to the 'bridges' list.
                    // We use Arrays.asList to create an immutable list for the bridge pair.
                    bridges.add(Arrays.asList(node, neigh));
                }
            } else {
                // If the neighbor has already been visited and is not the parent, it's a back edge.
                // Update the 'least' reachable time for the current 'node'.
                // We take the minimum of its current 'least' value and the discovery time of the neighbor ('found[neigh]').
                // This is because 'neigh' is an ancestor (or already visited node), and we can reach it.
                least[node] = Math.min(least[node], found[neigh]);
            }
        }
    }
}
```

## Interview Tips
*   **Explain the `found` and `least` arrays clearly**: Emphasize what each array represents and how they are updated.
*   **Walk through a small example**: Use a simple graph (e.g., 4-5 nodes) and trace the DFS, showing how `found`, `least`, and `bridges` change. This demonstrates your understanding.
*   **Discuss the bridge condition `found[node] < least[neigh]`**: Explain why this condition specifically identifies a bridge.
*   **Mention Tarjan's algorithm**: If you know the name, it shows familiarity with standard graph algorithms.
*   **Be prepared to discuss edge cases**: What if the graph is disconnected? (The current solution handles this by starting DFS from node 0; if disconnected, you'd need to iterate through all nodes to ensure all components are visited). What about graphs with no bridges?

## Revision Checklist
- [ ] Understand the definition of a bridge in a graph.
- [ ] Recall how DFS explores a graph and builds a DFS tree.
- [ ] Grasp the purpose of discovery time (`found` array).
- [ ] Understand the concept of the lowest reachable ancestor (`least` array).
- [ ] Be able to explain the condition `found[u] < least[v]` for identifying a bridge `(u, v)`.
- [ ] Implement graph representation (adjacency list).
- [ ] Handle parent node correctly in DFS.
- [ ] Correctly update `least` values from children and back edges.
- [ ] Consider disconnected graph scenarios (if applicable to problem variations).

## Similar Problems
*   [2-130. Number of Islands](https://leetcode.com/problems/number-of-islands/) (Graph traversal, DFS/BFS)
*   [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/) (This problem)
*   [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) (Cycle detection in directed graphs, related to DFS properties)
*   [117. Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/) (Tree traversal, but concepts of levels are relevant)

## Tags
`Graph` `Depth-First Search` `Tarjan's Algorithm` `Array` `Tree`

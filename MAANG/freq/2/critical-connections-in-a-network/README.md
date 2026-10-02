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
This problem asks to find all "critical connections" (bridges) in a network represented as an undirected graph. A bridge is an edge whose removal increases the number of connected components. We solve this using Depth First Search (DFS) and Tarjan's bridge-finding algorithm.

## Intuition
The core idea is to detect if an edge is a bridge during a DFS traversal. If, during the DFS from a node `u` to its neighbor `v`, we find that `v` and its descendants in the DFS tree cannot reach any ancestor of `u` (or `u` itself) through a back-edge, then the edge `(u, v)` must be a bridge. We can track this by maintaining two values for each node: `found` (the discovery time or level in DFS) and `least` (the earliest discovery time reachable from the node or its subtree, including back-edges). If `least[v] > found[u]`, it means `v` and its subtree cannot reach `u` or any of its ancestors, making `(u, v)` a bridge.

## Algorithm
1.  **Graph Representation**: Represent the network as an adjacency list where `graph[i]` stores all neighbors of node `i`.
2.  **Initialization**:
    *   Create `found` and `least` arrays of size `n` (number of nodes), initialized to -1 (or some indicator that they haven't been visited).
    *   Initialize `level` (or `time`) to 0.
    *   Initialize an empty list `bridges` to store the critical connections.
3.  **DFS Traversal**:
    *   Start a DFS from an arbitrary node (e.g., node 0) with its parent as -1.
    *   **Inside DFS(node, parent)**:
        *   Mark `node` as visited by setting `found[node] = least[node] = level++`.
        *   Iterate through each `neighbor` of `node`:
            *   If `neighbor` is the `parent`, skip it to avoid going back up the DFS tree immediately.
            *   If `neighbor` has not been visited (`found[neighbor] == -1`):
                *   Recursively call `dfs(neighbor, node)`.
                *   After the recursive call returns, update `least[node] = min(least[node], least[neighbor])`. This propagates the earliest reachable time from the subtree of `neighbor` up to `node`.
                *   **Bridge Condition**: If `found[node] < least[neighbor]`, it means `neighbor` and its subtree cannot reach `node` or any of its ancestors through a back-edge. Therefore, the edge `(node, neighbor)` is a bridge. Add `[node, neighbor]` to the `bridges` list.
            *   If `neighbor` has been visited (`found[neighbor] != -1`):
                *   This indicates a back-edge. Update `least[node] = min(least[node], found[neighbor])`. This means `node` can reach an ancestor (or itself) at `found[neighbor]` time.

## Concept to Remember
*   **Depth First Search (DFS)**: Essential for traversing graph structures and exploring paths.
*   **Graph Connectivity**: Understanding how edges contribute to or disconnect parts of a graph.
*   **Back-edges in DFS**: Identifying cycles and alternative paths by detecting edges that point to already visited ancestors.
*   **Tarjan's Bridge-Finding Algorithm**: A specific application of DFS to efficiently find bridges in a graph.

## Common Mistakes
*   **Incorrect Parent Handling**: Forgetting to skip the immediate parent in DFS can lead to incorrect `least` values and false positives/negatives.
*   **Misunderstanding `least` vs. `found`**: Confusing the discovery time (`found`) with the earliest reachable time (`least`) can lead to incorrect bridge detection logic.
*   **Not Handling Disconnected Graphs**: The provided solution assumes a connected graph. For disconnected graphs, DFS needs to be initiated from all unvisited nodes.
*   **Off-by-one Errors in `level`**: Incorrectly incrementing or using the `level` variable can skew discovery times.

## Complexity Analysis
- Time: O(V + E) - reason: The algorithm performs a single DFS traversal of the graph. Each vertex (V) and each edge (E) is visited at most a constant number of times.
- Space: O(V + E) - reason: The space is dominated by the adjacency list representation of the graph (O(V + E)), the recursion stack for DFS (O(V) in the worst case for a skewed graph), and the `found` and `least` arrays (O(V)).

## Commented Code
```java
class Solution {
    // Adjacency list to represent the graph. graph.get(i) will store a list of neighbors of node i.
    List<List<Integer>> graph = new ArrayList<>();
    // List to store the identified critical connections (bridges).
    List<List<Integer>> bridges = new ArrayList<>();
    // Array to store the discovery time (level) at which each node is first visited during DFS.
    int[] found;
    // Array to store the earliest discovery time reachable from a node or its subtree (including back-edges).
    int[] least;
    // A global counter to assign discovery times (levels) to nodes.
    int level = 0;

    // Main function to find critical connections.
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> con) {
        // Initialize the adjacency list for n nodes.
        for(int i = 0; i < n; i++) graph.add(new ArrayList<>());
        // Build the graph by adding edges from the input list 'con'.
        // Since it's an undirected graph, add edges in both directions.
        for(List<Integer> pair : con){
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }

        // Initialize 'found' array with -1, indicating no node has been visited yet.
        found = new int[n];
        Arrays.fill(found, -1);
        // Initialize 'least' array. It will be populated during DFS.
        least = new int[n];

        // Start DFS from node 0. The parent of the starting node is -1 (or any invalid node index).
        // We assume the graph is connected for this specific implementation.
        dfs(0, -1);

        // Return the list of identified bridges.
        return bridges;
    }

    // Depth First Search function to traverse the graph and find bridges.
    // 'node' is the current node being visited.
    // 'parent' is the node from which we arrived at 'node'.
    public void dfs(int node, int parent){
        // Mark the current node as visited and set its discovery time and initial 'least' value.
        // 'level' is incremented for each new node visited.
        found[node] = least[node] = level++;

        // Iterate through all neighbors of the current node.
        for(int neigh : graph.get(node)){
            // If the neighbor is the parent node, skip it to avoid going back immediately.
            if(parent == neigh) continue;

            // If the neighbor has not been visited yet (found[neigh] is -1).
            if(found[neigh] == -1){
                // Recursively call DFS on the unvisited neighbor.
                dfs(neigh, node);

                // After the recursive call returns, update the 'least' value of the current node.
                // The current node can reach at least as early as its neighbor's subtree can.
                least[node] = Math.min(least[node], least[neigh]);

                // Check for the bridge condition:
                // If the discovery time of the current node ('found[node]') is less than
                // the earliest reachable time from its neighbor's subtree ('least[neigh]'),
                // it means there's no back-edge from 'neigh' or its subtree to 'node' or any of its ancestors.
                // Therefore, the edge (node, neigh) is a critical connection (bridge).
                if(found[node] < least[neigh]) {
                    bridges.add(Arrays.asList(node, neigh));
                }
            } else {
                // If the neighbor has already been visited, it means we found a back-edge.
                // Update the 'least' value of the current node with the discovery time of the neighbor.
                // This signifies that the current node can reach an ancestor (or itself) at 'found[neigh]' time.
                least[node] = Math.min(least[node], found[neigh]);
            }
        }
    }
}
```

## Interview Tips
*   **Explain Tarjan's Algorithm**: Clearly articulate the logic behind `found` and `least` times and how they identify bridges.
*   **Trace an Example**: Be prepared to walk through a small graph example to show how the `found` and `least` values are updated and how a bridge is detected.
*   **Handle Edge Cases**: Discuss how you would handle disconnected graphs (by iterating through all nodes to start DFS if unvisited) or graphs with no edges.
*   **Clarify Constraints**: Ask about the maximum number of nodes and edges, and if the graph is guaranteed to be connected.

## Revision Checklist
- [ ] Understand the definition of a bridge in a graph.
- [ ] Implement DFS correctly with parent tracking.
- [ ] Correctly initialize `found` and `least` arrays.
- [ ] Understand the role of `level` (or `time`) in DFS.
- [ ] Implement the update logic for `least[node]` from `least[neighbor]` (tree edge).
- [ ] Implement the update logic for `least[node]` from `found[neighbor]` (back edge).
- [ ] Correctly apply the bridge condition: `found[node] < least[neighbor]`.
- [ ] Handle undirected edges by adding them in both directions in the adjacency list.
- [ ] Consider how to handle disconnected components if the problem statement implies it.

## Similar Problems
*   [2-11. Number of Islands](https://leetcode.com/problems/number-of-islands/) (Graph traversal, DFS/BFS)
*   [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/) (This problem)
*   [117. Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/) (Tree traversal, BFS)
*   [841. Keys and Rooms](https://leetcode.com/problems/keys-and-rooms/) (Graph traversal, DFS/BFS)

## Tags
`Graph` `Depth-First Search` `Tarjan's Algorithm` `Union-Find`

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] first = null;
        int[] second = null;
        
        // Step 1: Check for any node with two parents
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parent[v] != 0) {
                // node v has two parents: parent[v] and u
                first = new int[]{parent[v], v};
                second = new int[]{u, v};
                // Suppress the second edge temporarily
                edge[1] = 0;
            } else {
                parent[v] = u;
            }
        }
        
        // Step 2: Use Union-Find to detect cycles
        int[] uf = new int[n + 1];
        for (int i = 1; i <= n; i++) uf[i] = i;
        
        for (int[] edge : edges) {
            if (edge[1] == 0) continue; // Skip the suppressed edge
            int u = edge[0];
            int v = edge[1];
            
            int rootU = find(uf, u);
            int rootV = find(uf, v);
            
            if (rootU == rootV) {
                // Cycle detected!
                if (first == null) {
                    // Case 1: No node had two parents, the cycle edge is redundant
                    return edge;
                }
                // Case 2: A node had two parents, the first edge caused the cycle
                return first;
            }
            uf[rootV] = rootU;
        }
        
        // If no cycle was detected with the second edge suppressed, the second edge is the redundant one
        return second;
    }
    
    private int find(int[] uf, int i) {
        if (uf[i] == i) return i;
        return uf[i] = find(uf, uf[i]);
    }
}
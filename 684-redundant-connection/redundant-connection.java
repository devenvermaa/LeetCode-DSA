class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        
        // Initialize each node as its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        // Process each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            
            // If roots are the same, a cycle is detected
            if (rootU == rootV) {
                return edge;
            }
            
            // Otherwise, union the components
            parent[rootU] = rootV;
        }
        
        return new int[0];
    }
    
    // Find with path compression
    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent, parent[node]);
    }
}
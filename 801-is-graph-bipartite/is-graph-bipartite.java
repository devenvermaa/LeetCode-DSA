import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // Array to store colors: 0 means uncolored, 1 means color A, -1 means color B
        int[] colors = new int[n];
        
        // Loop through all nodes to handle disconnected graph components
        for (int i = 0; i < n; i++) {
            // If the node is already colored, skip it
            if (colors[i] != 0) {
                continue;
            }
            
            // Start BFS traversal for the uncolored component
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1; // Color the starting node with 1
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                
                // Check all adjacent neighbors of the current node
                for (int neighbor : graph[curr]) {
                    // If the neighbor has the same color, it's not a bipartite graph
                    if (colors[neighbor] == colors[curr]) {
                        return false;
                    }
                    
                    // If the neighbor is not colored, color it with the opposite color
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[curr];
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return true;
    }
}
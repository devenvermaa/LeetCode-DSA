import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] state = new int[n]; // 0 = unvisited, 1 = visiting, 2 = safe
        List<Integer> result = new ArrayList<>();
        
        // Check safety condition for each node
        for (int i = 0; i < n; i++) {
            if (isSafe(i, graph, state)) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private boolean isSafe(int node, int[][] graph, int[] state) {
        // If already evaluated, return true if it was marked safe (2)
        if (state[node] > 0) {
            return state[node] == 2;
        }
        
        // Mark node as currently visiting
        state[node] = 1;
        
        // Traverse all outgoing edges
        for (int neighbor : graph[node]) {
            // If any neighbor is trapped in a cycle, this node is unsafe
            if (!isSafe(neighbor, graph, state)) {
                return false;
            }
        }
        
        // No cycles found from this node, mark it safe
        state[node] = 2;
        return true;
    }
}
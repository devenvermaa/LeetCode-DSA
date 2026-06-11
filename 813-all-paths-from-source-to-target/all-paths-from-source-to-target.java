import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0); // Start at node 0
        
        dfs(0, graph, path, result);
        return result;
    }
    
    private void dfs(int node, int[][] graph, List<Integer> path, List<List<Integer>> result) {
        // Destination reached
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        // Explore neighbors
        for (int neighbor : graph[node]) {
            path.add(neighbor);
            dfs(neighbor, graph, path, result);
            path.remove(path.size() - 1); // Backtrack
        }
    }
}
import java.util.*;

public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Step 1: Build the graph using an adjacency list
        // Map: Node -> List of Pair(Neighbor, Edge Weight)
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double weight = values[i];
            
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            
            graph.get(u).put(v, weight);
            graph.get(v).put(u, 1.0 / weight);
        }
        
        // Step 2: Process each query using DFS
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            
            // If either variable doesn't exist in our equations, it's an invalid query
            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                results[i] = -1.0;
            } else if (src.equals(dest)) {
                results[i] = 1.0; // a / a is always 1.0
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(src, dest, graph, visited);
            }
        }
        
        return results;
    }
    
    private double dfs(String curr, String dest, Map<String, Map<String, Double>> graph, Set<String> visited) {
        // If we reached our target node, return 1.0 to wind down the product chain
        if (curr.equals(dest)) {
            return 1.0;
        }
        
        visited.add(curr);
        Map<String, Double> neighbors = graph.get(curr);
        
        for (Map.Entry<String, Double> neighbor : neighbors.entrySet()) {
            String nextNode = neighbor.getKey();
            double weight = neighbor.getValue();
            
            if (!visited.contains(nextNode)) {
                double productResult = dfs(nextNode, dest, graph, visited);
                // If a valid path to the destination was found downstream
                if (productResult != -1.0) {
                    return weight * productResult;
                }
            }
        }
        
        return -1.0; // No valid path found from this node
    }
}
import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Initialize a 26x26 distance matrix with a large value representing infinity
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0; // Cost to change a character to itself is 0
        }
        
        // Populate the graph with the given transformation costs
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        // Floyd-Warshall Algorithm to find all-pairs shortest paths
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] < Integer.MAX_VALUE && dist[k][j] < Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        // Calculate the total minimum cost to convert source to target
        long totalCost = 0;
        int n = source.length();
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            
            // If it's impossible to transform u to v, return -1
            if (dist[u][v] == Integer.MAX_VALUE) {
                return -1;
            }
            totalCost += dist[u][v];
        }
        
        return totalCost;
    }
}
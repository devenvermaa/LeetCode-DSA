import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. Build the adjacency list for the graph: u -> List of {v, price}
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            adj.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }
        
        // 2. Distance array to store the minimum cost to reach each city
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        
        // 3. BFS Queue stores arrays of {current_city, current_cost}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        
        // Track the number of stops made
        int stops = 0;
        
        // Loop while we have paths to explore and haven't exceeded k stops
        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            
            // Process all nodes at the current level (same number of stops)
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int u = curr[0];
                int cost = curr[1];
                
                if (!adj.containsKey(u)) continue;
                
                // Explore neighbors
                for (int[] neighbor : adj.get(u)) {
                    int v = neighbor[0];
                    int price = neighbor[1];
                    
                    // Relax the edge if a cheaper path is found
                    if (cost + price < prices[v]) {
                        prices[v] = cost + price;
                        queue.offer(new int[]{v, prices[v]});
                    }
                }
            }
            stops++;
        }
        
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}
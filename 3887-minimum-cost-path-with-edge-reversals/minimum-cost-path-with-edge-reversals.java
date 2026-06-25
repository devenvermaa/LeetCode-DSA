import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        // Step 1: Build the adjacency list graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // Original edge: u -> v with cost w
            adj.get(u).add(new int[]{v, w});
            // Reversed edge: v -> u with cost 2 * w
            adj.get(v).add(new int[]{u, 2 * w});
        }
        
        // Step 2: Initialize Dijkstra structures
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        // Min-Priority Queue tracking [node, current_distance]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});
        
        // Step 3: Run Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];
            
            // Skip if we found a shorter path to u already
            if (d > dist[u]) continue;
            
            // If we reached the target node, we can immediately return its minimum distance
            if (u == n - 1) return dist[u];
            
            // Relax neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        
        // Step 4: Return -1 if destination node n - 1 is unreachable
        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }
}
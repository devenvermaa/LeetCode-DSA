import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int INF = 1_000_000_000;

        // Find the maximum value in the grid to size our suffix array
        int maxVal = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxVal = Math.max(maxVal, grid[i][j]);
            }
        }

        // Group cells by their grid values
        List<int[]>[] valueToCells = new ArrayList[maxVal + 1];
        for (int v = 0; v <= maxVal; v++) {
            valueToCells[v] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valueToCells[grid[i][j]].add(new int[]{i, j});
            }
        }

        // dp[t][i][j] represents min cost to reach (i, j) with exactly 't' teleports
        int[][][] dp = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[t][i], INF);
            }
        }

        // --- Base Case: 0 Teleportations (Standard DP) ---
        dp[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && dp[0][i - 1][j] != INF) {
                    dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0 && dp[0][i][j - 1] != INF) {
                    dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        // --- Layered DP for 1 to K Teleportations ---
        for (int t = 1; t <= k; t++) {
            // Build the suffix minimum array based on layer (t-1)
            // suffixMin[x] = minimum cost from previous layer among all cells with value >= x
            int[] suffixMin = new int[maxVal + 1];
            int currentMin = INF;
            for (int v = maxVal; v >= 0; v--) {
                for (int[] cell : valueToCells[v]) {
                    currentMin = Math.min(currentMin, dp[t - 1][cell[0]][cell[1]]);
                }
                suffixMin[v] = currentMin;
            }

            // Calculate costs for current layer 't'
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int val = grid[i][j];

                    // Option 1: Teleport to this cell from a valid cell in the previous layer
                    dp[t][i][j] = Math.min(dp[t - 1][i][j], suffixMin[val]);

                    // Option 2: Move normally from top neighbor
                    if (i > 0 && dp[t][i - 1][j] != INF) {
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i - 1][j] + val);
                    }

                    // Option 3: Move normally from left neighbor
                    if (j > 0 && dp[t][i][j - 1] != INF) {
                        dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j - 1] + val);
                    }
                }
            }
        }

        // Return the minimum cost to reach (m-1, n-1) using up to k teleports
        int ans = INF;
        for (int t = 0; t <= k; t++) {
            ans = Math.min(ans, dp[t][m - 1][n - 1]);
        }

        return ans;
    }
}
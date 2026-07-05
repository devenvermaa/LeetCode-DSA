class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dpScore[i][j] stores the max score from (0,0) to (i,j)
        int[][] dpScore = new int[n][n];
        // dpPaths[i][j] stores the number of paths achieving that max score
        int[][] dpPaths = new int[n][n];
        
        // Base case: at the destination 'E' (0, 0), max score is 0 and 1 valid path exists.
        dpPaths[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Skip starting point calculation (handled as base case) and obstacles
                if ((i == 0 && j == 0) || board.get(i).charAt(j) == 'X') {
                    continue;
                }
                
                int currentVal = 0;
                char c = board.get(i).charAt(j);
                if (Character.isDigit(c)) {
                    currentVal = c - '0';
                }
                
                int maxPrevScore = -1;
                int totalPaths = 0;
                
                // Check all 3 incoming directions: Up (i-1, j), Left (i, j-1), Up-Left (i-1, j-1)
                int[][] directions = {{i - 1, j}, {i, j - 1}, {i - 1, j - 1}};
                
                for (int[] dir : directions) {
                    int prevI = dir[0];
                    int prevJ = dir[1];
                    
                    // Ensure the previous cell is within bounds and reachable
                    if (prevI >= 0 && prevJ >= 0 && dpPaths[prevI][prevJ] > 0) {
                        if (dpScore[prevI][prevJ] > maxPrevScore) {
                            maxPrevScore = dpScore[prevI][prevJ];
                            totalPaths = dpPaths[prevI][prevJ];
                        } else if (dpScore[prevI][prevJ] == maxPrevScore) {
                            totalPaths = (totalPaths + dpPaths[prevI][prevJ]) % MOD;
                        }
                    }
                }
                
                // If maxPrevScore remains -1, it means this cell is completely unreachable
                if (maxPrevScore != -1) {
                    dpScore[i][j] = maxPrevScore + currentVal;
                    dpPaths[i][j] = totalPaths;
                }
            }
        }
        
        // The answer will be collected at the bottom-right corner 'S' (n-1, n-1)
        return new int[]{dpScore[n - 1][n - 1], dpPaths[n - 1][n - 1]};
    }
}
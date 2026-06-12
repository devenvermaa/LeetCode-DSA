class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] maxRow = new int[n];
        int[] maxCol = new int[n];
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                maxRow[r] = Math.max(maxRow[r], grid[r][c]);
                maxCol[c] = Math.max(maxCol[c], grid[r][c]);
            }
        }
        
        int totalIncrease = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                totalIncrease += Math.min(maxRow[r], maxCol[c]) - grid[r][c];
            }
        }
        
        return totalIncrease;
    }
}
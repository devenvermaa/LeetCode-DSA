class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] will store the maximum number of strings that can be formed
        // using at most i '0's and j '1's
        int[][] dp = new int[m + 1][n + 1];
        
        for (String s : strs) {
            int[] count = countZeroesOnes(s);
            int zeroes = count[0];
            int ones = count[1];
            
            // Iterate backwards to avoid using the same string multiple times
            for (int i = m; i >= zeroes; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroes][j - ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
    
    // Helper method to count '0's and '1's in a string
    private int[] countZeroesOnes(String s) {
        int[] count = new int[2];
        for (char c : s.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }
}
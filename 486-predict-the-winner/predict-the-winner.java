class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i][j] stores the maximum relative score difference the current player 
        // can achieve over the opponent from the subarray nums[i...j]
        int[][] dp = new int[n][n];
        
        // Base case: when there's only one element, the player must pick it
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // Fill the DP table by checking subarrays of increasing lengths
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                // Maximize score by subtracting the opponent's optimal future score
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        
        // If Player 1's relative net score advantage is >= 0, they can win
        return dp[0][n - 1] >= 0;
    }
}
class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] prefixSum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        // memo[i][m] stores max sum partitioning nums[i:] into m groups
        Double[][] memo = new Double[n][k + 1];
        return dp(0, k, nums, prefixSum, memo);
    }
    
    private double dp(int i, int k, int[] nums, double[] prefixSum, Double[][] memo) {
        int n = nums.length;
        if (i == n) return 0;
        if (k == 1) {
            return (prefixSum[n] - prefixSum[i]) / (n - i);
        }
        if (memo[i][k] != null) return memo[i][k];
        
        double maxVal = 0;
        for (int j = i; j <= n - k; j++) {
            double currentAverage = (prefixSum[j + 1] - prefixSum[i]) / (j - i + 1);
            maxVal = Math.max(maxVal, currentAverage + dp(j + 1, k - 1, nums, prefixSum, memo));
        }
        
        return memo[i][k] = maxVal;
    }
}
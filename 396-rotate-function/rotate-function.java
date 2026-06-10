public class Solution {
    public int maxRotateFunction(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int sumOfElements = 0;
        int f = 0; // Tracks F(k)
        
        // Step 1: Calculate total array sum and the base function value F(0)
        for (int i = 0; i < n; i++) {
            sumOfElements += nums[i];
            f += i * nums[i];
        }
        
        int maxVal = f;
        
        // Step 2: Iteratively compute F(1) to F(n-1) using the math relation
        for (int i = n - 1; i > 0; i--) {
            // F(k) = F(k-1) + sum - n * nums[last_idx_of_prev_rotation]
            f = f + sumOfElements - (n * nums[i]);
            maxVal = Math.max(maxVal, f);
        }
        
        return maxVal;
    }
}
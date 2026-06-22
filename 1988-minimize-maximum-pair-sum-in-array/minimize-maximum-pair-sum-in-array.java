import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        int maxPairSum = 0;
        int left = 0;
        int right = nums.length - 1;
        
        // Step 2: Use two pointers to pair the smallest with the largest
        while (left < right) {
            int currentPairSum = nums[left] + nums[right];
            maxPairSum = Math.max(maxPairSum, currentPairSum);
            left++;
            right--;
        }
        
        return maxPairSum;
    }
}
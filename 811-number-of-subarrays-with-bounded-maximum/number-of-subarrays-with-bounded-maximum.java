class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int result = 0;
        int lastValidCount = 0;
        int start = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                // Completely breaks subarray continuation
                start = i;
                lastValidCount = 0;
            } else if (nums[i] >= left) {
                // Valid maximum element found, record how many subarrays can end here
                lastValidCount = i - start;
                result += lastValidCount;
            } else {
                // Element is smaller than 'left', can only extend existing valid subarrays
                result += lastValidCount;
            }
        }
        
        return result;
    }
}
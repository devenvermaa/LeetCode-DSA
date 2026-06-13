class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int maxLen = 1, currentLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }
        return Math.max(maxLen, currentLen);
    }
}
class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        
        // State variables for the previous index (initially index 0)
        int notSwap = 0; // Cost if we don't swap at index 0
        int swap = 1;    // Cost if we do swap at index 0
        
        for (int i = 1; i < n; i++) {
            int nextNotSwap = Integer.MAX_VALUE;
            int nextSwap = Integer.MAX_VALUE;
            
            // Case 1: Elements are already self-increasing without altering arrays
            // nums1: ... 3 -> 5 ...
            // nums2: ... 2 -> 4 ...
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                // If we don't swap now, we shouldn't have swapped previously
                nextNotSwap = Math.min(nextNotSwap, notSwap);
                // If we swap now, we must have swapped previously to maintain order
                nextSwap = Math.min(nextSwap, swap + 1);
            }
            
            // Case 2: Elements become valid if we interchange their relative array positions
            // nums1: ... 3 -> 4 ...
            // nums2: ... 5 -> 6 ...
            if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                // If we don't swap now, we MUST have swapped previously
                nextNotSwap = Math.min(nextNotSwap, swap);
                // If we swap now, we must NOT have swapped previously
                nextSwap = Math.min(nextSwap, notSwap + 1);
            }
            
            // Move state tracking forward
            notSwap = nextNotSwap;
            swap = nextSwap;
        }
        
        return Math.min(notSwap, swap);
    }
}
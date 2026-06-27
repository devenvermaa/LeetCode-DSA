import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        int maxNum = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (num > maxNum) maxNum = num;
        }
        
        // Handle the special case of 1s (must take an odd count to stay symmetric)
        int ans = count.containsKey(1) ? count.get(1) - (count.get(1) % 2 == 0 ? 1 : 0) : 1;
        
        for (int num : nums) {
            if (num == 1) continue;
            
            int length = 0;
            long x = num;
            
            // Build the sequence x, x^2, x^4... as long as we have at least 2 copies
            while (x <= maxNum && count.containsKey((int) x) && count.get((int) x) >= 2) {
                length += 2;
                x *= x; 
            }
            
            // If the current peak element exists at least once, it can be the middle
            if (x <= maxNum && count.containsKey((int) x)) {
                length += 1;
            } else {
                // Otherwise, the previous element must act as the peak (subtract 1 from padding)
                length -= 1;
            }
            
            ans = Math.max(ans, length);
        }
        
        return ans;
    }
}
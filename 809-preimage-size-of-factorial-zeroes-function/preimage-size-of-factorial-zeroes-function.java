class Solution {
    public int preimageSizeFZF(int k) {
        // The answer is always 5 if k is reachable, otherwise 0.
        // We use binary search to check if any number has exactly k trailing zeroes.
        long left = 0;
        long right = 5L * (k + 1);
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long zeroes = trailingZeroes(mid);
            
            if (zeroes == k) {
                return 5;
            } else if (zeroes < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return 0;
    }
    
    // Helper function to count trailing zeroes in x! (Legendre's formula)
    private long trailingZeroes(long x) {
        long count = 0;
        while (x >= 5) {
            count += x / 5;
            x /= 5;
        }
        return count;
    }
}
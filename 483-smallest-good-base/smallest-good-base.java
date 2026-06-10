class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        
        // m represents the number of '1's in the base k representation.
        // We start from the maximum possible number of bits down to 2.
        for (int m = 62; m >= 2; m--) {
            long left = 2;
            // Precise upper bound constraint for radix calculation
            long right = (long) Math.pow(num, 1.0 / (m - 1)) + 1;
            
            while (left <= right) {
                long mid = left + (right - left) / 2;
                long sum = 0;
                long currentPower = 1;
                boolean overflow = false;
                
                // Calculate the value represented by m ones in base 'mid'
                for (int i = 0; i < m; i++) {
                    sum += currentPower;
                    if (i < m - 1) {
                        // Check for multiplication overflow
                        if (currentPower > num / mid) {
                            overflow = true;
                            break;
                        }
                        currentPower *= mid;
                    }
                }
                
                if (overflow || sum > num) {
                    right = mid - 1; // Base is too large
                } else if (sum < num) {
                    left = mid + 1;  // Base is too small
                } else {
                    return String.valueOf(mid); // Found the valid smallest base
                }
            }
        }
        
        // Base case fallback: n represented in base n-1 is always "11"
        return String.valueOf(num - 1);
    }
}
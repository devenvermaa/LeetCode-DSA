class Solution {
    public int numTilings(int n) {
        // Base cases
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        
        int MOD = 1000000007;
        
        // We only need the last 3 states to calculate the next state
        long dummy0 = 1; // f(1)
        long dummy1 = 2; // f(2)
        long dummy2 = 5; // f(3)
        
        for (int i = 4; i <= n; i++) {
            // f(n) = (2 * f(n-1) + f(n-3)) % MOD
            long current = (2 * dummy2 + dummy0) % MOD;
            
            // Shift the state variables forward
            dummy0 = dummy1;
            dummy1 = dummy2;
            dummy2 = current;
        }
        
        return (int) dummy2;
    }
}
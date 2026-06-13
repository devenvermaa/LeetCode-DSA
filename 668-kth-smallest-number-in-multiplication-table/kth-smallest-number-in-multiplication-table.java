class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countLessOrEqual(mid, m, n) >= k) {
                high = mid; // Try to find a smaller valid number
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private int countLessOrEqual(int target, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(target / i, n);
        }
        return count;
    }
}
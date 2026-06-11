class Solution {
    public int kthGrammar(int n, int k) {
        // Base case: The first row always starts with 0
        if (n == 1) {
            return 0;
        }
        
        // Find the total number of elements in the current row n
        // Total elements = 2^(n-1), so the midpoint is 2^(n-2)
        int mid = (1 << (n - 2));
        
        // If k is in the first half, it's the same as the k-th element in row n-1
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        } else {
            // If k is in the second half, it's the flipped version of the corresponding element in the first half
            return 1 - kthGrammar(n - 1, k - mid);
        }
    }
}
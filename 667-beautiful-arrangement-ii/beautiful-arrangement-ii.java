class Solution {
    public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int left = 1, right = n;
        int index = 0;
        
        // Alternate to create k-1 distinct differences
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0) {
                result[index++] = left++;
            } else {
                result[index++] = right--;
            }
        }
        
        // Fill the rest sequentially to maintain the final difference of 1
        if (k % 2 == 0) {
            while (index < n) {
                result[index++] = right--;
            }
        } else {
            while (index < n) {
                result[index++] = left++;
            }
        }
        
        return result;
    }
}
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // The maximum possible prefix sum is +n, and minimum is -n.
        // We add an offset to handle negative indices safely in the Fenwick Tree.
        int offset = n + 2; 
        int fenwickSize = 2 * n + 5;
        int[] fenwick = new int[fenwickSize];
        
        int balance = 0;
        long totalSubarrays = 0;
        
        // Initialize the Fenwick tree with the initial balance of 0
        update(fenwick, offset, 1);
        
        for (int num : nums) {
            // Step 1: Transform element to +1 or -1
            balance += (num == target) ? 1 : -1;
            
            // Step 2: Count all previous prefix balances strictly less than the current balance
            totalSubarrays += query(fenwick, offset + balance - 1);
            
            // Step 3: Insert the current balance into the Fenwick tree
            update(fenwick, offset + balance, 1);
        }
        
        return (int) totalSubarrays;
    }
    
    // Fenwick Tree: Add value at a specific index
    private void update(int[] fenwick, int index, int value) {
        while (index < fenwick.length) {
            fenwick[index] += value;
            index += index & -index;
        }
    }
    
    // Fenwick Tree: Get prefix sum up to a specific index
    private int query(int[] fenwick, int index) {
        int sum = 0;
        while (index > 0) {
            sum += fenwick[index];
            index -= index & -index;
        }
        return sum;
    }
}
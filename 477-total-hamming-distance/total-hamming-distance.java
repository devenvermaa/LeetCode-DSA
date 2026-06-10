class Solution {
    public int totalHammingDistance(int[] nums) {
        int totalDistance = 0;
        int n = nums.length;
        
        // Loop through each of the 32 bit positions
        for (int i = 0; i < 32; i++) {
            int bitCountOnes = 0;
            
            // Count how many numbers have the i-th bit set to 1
            for (int num : nums) {
                // Right shift by i and check if the last bit is 1
                bitCountOnes += (num >> i) & 1;
            }
            
            // Numbers with 0 at the i-th bit
            int bitCountZeroes = n - bitCountOnes;
            
            // Each 1 paired with a 0 adds 1 to the total Hamming distance
            totalDistance += bitCountOnes * bitCountZeroes;
        }
        
        return totalDistance;
    }
}
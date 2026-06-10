class Solution {
    public int findComplement(int num) {
        // Find the highest set bit, shift left by 1, and subtract 1 
        // to create a mask of 1s of the same bit-length as num.
        // We use long cast to prevent integer overflow if the highest bit is at MSB.
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        
        // XORing with the mask flips all the bits within the range
        return num ^ mask;
    }
}
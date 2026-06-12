class Solution {
    public boolean xorGame(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        // Alice wins if the total XOR sum is already 0, OR if the number of elements is even.
        return xorSum == 0 || nums.length % 2 == 0;
    }
}
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] result = new int[nums.length];
        int posIdx = 0;
        int negIdx = 1;
        
        for (int num : nums) {
            if (num > 0) {
                result[posIdx] = num;
                posIdx += 2; // Move to the next even index
            } else {
                result[negIdx] = num;
                negIdx += 2; // Move to the next odd index
            }
        }
        
        return result;
    }
}
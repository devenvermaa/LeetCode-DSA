import java.util.Random;

class Solution {
    private int[] nums;
    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int result = -1;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Only process elements that match our target
            if (nums[i] == target) {
                count++;
                // Generate a random number between 0 (inclusive) and count (exclusive)
                // The condition rand.nextInt(count) == 0 happens with probability 1/count
                if (rand.nextInt(count) == 0) {
                    result = i;
                }
            }
        }
        
        return result;
    }
}
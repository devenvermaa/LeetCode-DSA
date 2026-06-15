class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            
            if (currentSum == target) {
                // The problem requires 1-indexed results
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                left++; // Sum is too small, move the left pointer rightward
            } else {
                right--; // Sum is too large, move the right pointer leftward
            }
        }
        
        // Return an empty array if no solution is found (though the problem guarantees one exists)
        return new int[]{-1, -1};
    }
}
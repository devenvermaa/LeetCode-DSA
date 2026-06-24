import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array
        Arrays.sort(arr);
        
        int minDiff = Integer.MAX_VALUE;
        
        // Step 2: Single pass to find min difference and track pairs
        for (int i = 0; i < arr.length - 1; i++) {
            int currentDiff = arr[i + 1] - arr[i];
            
            // If we found a smaller difference, clear previous pairs and update minDiff
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
                result.clear(); // Previous pairs are no longer valid
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            } 
            // If the difference matches the current minimum, add the pair
            else if (currentDiff == minDiff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        
        return result;
    }
}
import java.util.Arrays;

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        // Sort the heaters to enable binary search
        Arrays.sort(heaters);
        
        int result = 0;
        
        for (int house : houses) {
            // Binary search returns the index of the heater if found.
            // If not found, it returns (-(insertion point) - 1)
            int index = Arrays.binarySearch(heaters, house);
            
            if (index < 0) {
                // Convert to the actual insertion point index
                int insertionPoint = -(index + 1);
                
                // Distance to the closest heater on the right (if it exists)
                int distRight = (insertionPoint < heaters.length) ? 
                                heaters[insertionPoint] - house : Integer.MAX_VALUE;
                                
                // Distance to the closest heater on the left (if it exists)
                int distLeft = (insertionPoint > 0) ? 
                               house - heaters[insertionPoint - 1] : Integer.MAX_VALUE;
                
                // The house will choose the closer of the two heaters
                int closestHeaterDist = Math.min(distLeft, distRight);
                
                // The global radius must be large enough to cover this house
                result = Math.max(result, closestHeaterDist);
            }
            // If index >= 0, the house is exactly on a heater, so distance is 0 (no-op)
        }
        
        return result;
    }
}